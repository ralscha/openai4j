/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.rasc.openai4j.chatcompletions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.rasc.openai4j.chatcompletions.ChatCompletionsResponse.Choice.FinishReason;
import feign.Headers;
import feign.RequestLine;

public interface ChatCompletionsClient {

	/**
	 * Creates a completion for the provided prompt and parameters.
	 *
	 * @return Returns a completion object
	 */
	@RequestLine("POST /chat/completions")
	@Headers("Content-Type: application/json")
	ChatCompletionsResponse create(ChatCompletionsCreateRequest request);

	/**
	 * Creates a completion for the provided prompt and parameters.
	 *
	 * @return Returns a completion object
	 */
	default ChatCompletionsResponse create(
			Function<ChatCompletionsCreateRequest.Builder, ChatCompletionsCreateRequest.Builder> fn) {
		return this.create(fn.apply(ChatCompletionsCreateRequest.builder()).build());
	}

	default ChatCompletionsResponse create(
			Function<ChatCompletionsCreateRequest.Builder, ChatCompletionsCreateRequest.Builder> fn,
			List<JavaFunction<?, ?>> javaFunctions, ObjectMapper objectMapper,
			int maxLoop) throws JsonMappingException, JsonProcessingException {
		Map<String, JavaFunction<?, ?>> javaFunctionRegistry = new HashMap<>();
		List<ChatCompletionTool> tools = new ArrayList<>();
		for (JavaFunction<?, ?> javaFunction : javaFunctions) {
			javaFunctionRegistry.put(javaFunction.name(), javaFunction);
			tools.add(javaFunction.toTool());
		}

		var request = fn.apply(ChatCompletionsCreateRequest.builder()).addAllTools(tools)
				.build();
		ChatCompletionsResponse response = this.create(request);

		var thread = new ArrayList<>(request.messages());
		var choice = response.choices()[0];

		int loop = 1;
		while (choice.finishReason() == FinishReason.TOOL_CALLS) {
			if (loop > maxLoop) {
				return response;
			}

			var message = choice.message();
			thread.add(AssistantMessage.of(choice.message()));

			for (var toolCall : message.toolCalls()) {
				JavaFunction<?, ?> javaFunction = javaFunctionRegistry
						.get(toolCall.function().name());
				if (javaFunction != null) {
					var argument = objectMapper.readValue(toolCall.function().arguments(),
							javaFunction.parameterClass());
					Object result = javaFunction.call(argument);

					if (result != null) {
						String resultJson = objectMapper.writeValueAsString(result);
						thread.add(ToolMessage.of(toolCall.id(), resultJson));
					}
					else {
						thread.add(ToolMessage.of(toolCall.id(), null));
					}

				}
				else {
					throw new IllegalStateException(
							"Unknown function " + toolCall.function().name());
				}
			}

			request = request.withMessages(thread);
			response = this.create(request);

			loop += 1;
		}

		return response;
	}
}
