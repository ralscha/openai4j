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
import java.util.Set;
import java.util.function.Function;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;

import ch.rasc.openai4j.chatcompletions.ChatCompletionsCreateRequest.ResponseFormat;
import ch.rasc.openai4j.chatcompletions.ChatCompletionsCreateRequest.ToolChoice;
import ch.rasc.openai4j.chatcompletions.ChatCompletionsResponse.Choice.FinishReason;
import ch.rasc.openai4j.common.FunctionParameters;
import ch.rasc.openai4j.common.ToolCall;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * High level chat completions client that supports calling Java methods
 */
public class ChatCompletionsService {

	private final SchemaGenerator schemaGenerator;

	private final ChatCompletionsClient chatCompletionsClient;

	private final ObjectMapper objectMapper;

	private final Validator validator;

	public ChatCompletionsService(ChatCompletionsClient chatCompletionsClient,
			ObjectMapper objectMapper) {
		JacksonModule module = new JacksonModule(
				JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
		SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(
				SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON).with(module);
		SchemaGeneratorConfig config = configBuilder.build();
		this.schemaGenerator = new SchemaGenerator(config);
		this.chatCompletionsClient = chatCompletionsClient;
		this.objectMapper = objectMapper;

		try (ValidatorFactory validatorFactory = Validation.byDefaultProvider()
				.configure().messageInterpolator(new ParameterMessageInterpolator())
				.buildValidatorFactory()) {
			this.validator = validatorFactory.getValidator();
		}
	}

	public ChatCompletionsService(ChatCompletionsClient chatCompletionsClient) {
		this(chatCompletionsClient, new ObjectMapper());
	}

	/**
	 * Creates and calls a chat completion for the provided prompt and parameters. When
	 * the response of the completion contains a tool call, the tool call is executed and
	 * the completion is called again with the updated prompt.
	 * <p>
	 * The method will repeat this process until the completion is finished or the maximum
	 * number of iterations is reached.
	 *
	 * @param fn A chat completion request builder function
	 * @param javaFunctions A list of java functions that can be called from the chat
	 * completion
	 * @param maxIterations The maximum number of iterations to call the java functions.
	 * Set it to a reasonable value to avoid infinite loops.
	 * @return A chat completion response
	 * @throws JsonProcessingException If the java function arguments or results cannot be
	 * serialized or deserialized
	 */
	public ChatCompletionsResponse create(
			Function<ChatCompletionsCreateRequest.Builder, ChatCompletionsCreateRequest.Builder> fn,
			List<JavaFunction<?, ?>> javaFunctions, int maxIterations)
			throws JsonProcessingException {
		Map<String, JavaFunction<?, ?>> javaFunctionRegistry = new HashMap<>();
		List<ChatCompletionTool> tools = new ArrayList<>();
		for (JavaFunction<?, ?> javaFunction : javaFunctions) {
			javaFunctionRegistry.put(javaFunction.name(), javaFunction);
			tools.add(javaFunction.toTool(this.schemaGenerator));
		}

		var requestBuilder = fn.apply(ChatCompletionsCreateRequest.builder());
		var request = requestBuilder.tools(tools).build();
		ChatCompletionsResponse response = this.chatCompletionsClient.create(request);

		var thread = new ArrayList<>(request.messages());
		var choice = response.choices().get(0);

		int iterationCount = 1;
		while (choice.finishReason() == FinishReason.TOOL_CALLS) {
			if (iterationCount > maxIterations) {
				return response;
			}

			var message = choice.message();
			thread.add(AssistantMessage.of(choice.message()));

			for (var toolCall : message.toolCalls()) {
				JavaFunction<?, ?> javaFunction = javaFunctionRegistry
						.get(toolCall.function().name());
				if (javaFunction == null) {
					throw new IllegalStateException(
							"Unknown function " + toolCall.function().name());
				}
				var argument = this.objectMapper.readValue(
						toolCall.function().arguments(), javaFunction.parameterClass());
				Object result = javaFunction.call(argument);

				if (result != null) {
					String resultJson = this.objectMapper.writeValueAsString(result);
					thread.add(ToolMessage.of(toolCall.id(), resultJson));
				}
				else {
					thread.add(ToolMessage.of(toolCall.id(), null));
				}
			}

			request = requestBuilder.messages(thread).build();
			response = this.chatCompletionsClient.create(request);

			iterationCount += 1;
		}

		return response;
	}

	public record ChatCompletionsModelResponse<T>(ChatCompletionsResponse response,
			T responseModel, String error) {
	}

	public enum Mode {
		TOOL, JSON
	}

	public <T> ChatCompletionsModelResponse<T> create(
			Function<ChatCompletionsCreateRequest.Builder, ChatCompletionsCreateRequest.Builder> fn,
			Class<T> responseModel, Mode mode, int maxRetries) {

		if (maxRetries <= 0) {
			throw new IllegalArgumentException("maxRetries must be greater than 0");
		}

		var requestBuilder = fn.apply(ChatCompletionsCreateRequest.builder());
		ObjectNode jsonSchema = this.schemaGenerator.generateSchema(responseModel);

		if (mode == Mode.JSON) {

			requestBuilder.responseFormat(ResponseFormat.JSON_OBJECT);

			String jsonSchemaSystemMessage = "Make sure that your response to any message matches the json_schema below, "
					+ "do not deviate at all: \n" + jsonSchema;

			List<ChatCompletionMessage> originalMessages = requestBuilder.messages();
			List<ChatCompletionMessage> thread = new ArrayList<>();

			if (!originalMessages.isEmpty() && originalMessages
					.get(0) instanceof SystemMessage firstSystemMessage) {
				thread.add(SystemMessage.of(
						firstSystemMessage.content() + "\n\n" + jsonSchemaSystemMessage));
				thread.addAll(originalMessages.subList(1, originalMessages.size()));
			}
			else {
				thread.add(SystemMessage.of(jsonSchemaSystemMessage));
				thread.addAll(originalMessages);
			}

			return executeRequest(responseModel, maxRetries, requestBuilder, mode, thread,
					null);
		}

		List<ChatCompletionMessage> thread = new ArrayList<>(requestBuilder.messages());

		JsonNode descriptionNode = jsonSchema.get("description");
		String description = null;
		if (descriptionNode != null) {
			description = descriptionNode.textValue();
		}
		String functionName = responseModel.getSimpleName();
		requestBuilder.tools(List.of(ChatCompletionTool
				.of(FunctionParameters.of(functionName, description, jsonSchema))));
		requestBuilder.toolChoice(ToolChoice.function(functionName));
		return executeRequest(responseModel, maxRetries, requestBuilder, mode, thread,
				functionName);
	}

	private <T> ChatCompletionsModelResponse<T> executeRequest(Class<T> responseModel,
			int maxRetries, ChatCompletionsCreateRequest.Builder requestBuilder,
			Mode mode, List<ChatCompletionMessage> thread, String functionName) {

		int retryCount = 0;

		ChatCompletionsResponse response = null;
		while (retryCount < maxRetries) {
			response = this.chatCompletionsClient
					.create(requestBuilder.messages(thread).build());

			var choice = response.choices().get(0);
			if (choice.finishReason() != FinishReason.STOP) {
				return new ChatCompletionsModelResponse<>(response, null,
						"finish reason not STOP");
			}

			try {
				T responseModelInstance = null;
				if (mode == Mode.JSON) {
					responseModelInstance = this.objectMapper
							.readValue(choice.message().content(), responseModel);
				}
				else {
					ToolCall firstToolCall = choice.message().toolCalls().get(0);
					if (firstToolCall.function().name().equals(functionName)) {
						responseModelInstance = this.objectMapper.readValue(
								firstToolCall.function().arguments(), responseModel);
					}
					else {
						String errorMessage = "Recall the correct function, function "
								+ firstToolCall.function().name() + " does not exist";
						thread.add(AssistantMessage.of(choice.message()));
						thread.add(UserMessage.of(errorMessage));
					}
				}

				if (responseModelInstance != null) {
					Set<ConstraintViolation<T>> constraintViolations = this.validator
							.validate(responseModelInstance);
					if (constraintViolations.isEmpty()) {
						return new ChatCompletionsModelResponse<>(response,
								responseModelInstance, null);
					}

					StringBuilder validationErrors;
					if (mode == Mode.JSON) {
						validationErrors = new StringBuilder("Validation errors found\n");
					}
					else {
						validationErrors = new StringBuilder(
								"Recall the function correctly, validation errors found\n");
					}
					for (ConstraintViolation<T> constraintViolation : constraintViolations) {
						validationErrors.append(constraintViolation.getPropertyPath())
								.append(": ").append(constraintViolation.getMessage())
								.append("\n");
					}
					thread.add(AssistantMessage.of(choice.message()));
					thread.add(UserMessage.of(validationErrors.toString()));
				}
			}
			catch (JsonProcessingException e) {
				String errorMessage;
				if (mode == Mode.JSON) {
					errorMessage = "Could not deserialize response\n" + e.getMessage();
				}
				else {
					errorMessage = "Recall the function correctly, exceptions during deserialization found\n"
							+ e.getMessage();
				}
				thread.add(AssistantMessage.of(choice.message()));
				thread.add(UserMessage.of(errorMessage));
			}

			retryCount++;
		}

		return new ChatCompletionsModelResponse<>(response, null, "max retries reached");
	}
}
