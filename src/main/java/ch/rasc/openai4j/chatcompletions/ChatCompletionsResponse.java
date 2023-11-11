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

import ch.rasc.openai4j.Nullable;
import ch.rasc.openai4j.common.FunctionArguments;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Represents a chat completion response returned by model, based on the provided input.
 */
public record ChatCompletionsResponse(String id, Choice[] choices, int created,
		String model,
		@Nullable @JsonProperty("system_fingerprint") String systemFingerprint,
		String object, Usage usage) {

	public record Choice(int index,
			@JsonProperty("finish_reason") FinishReason finishReason, Message message) {

		public enum FinishReason {
			STOP("stop"), LENGTH("length"), CONTENT_FILTER("content_filter"),
			TOOL_CALLS("tool_calls"), FUNCTION_CALL("function_call");

			private final String value;

			FinishReason(String value) {
				this.value = value;
			}

			@JsonValue
			public String value() {
				return this.value;
			}
		}
	}

	public record Message(@Nullable String content,
			@Nullable @JsonProperty("tool_calls") ToolCall[] toolCalls, String role) {
	}

	public record ToolCall(String id, String type, FunctionArguments function) {
	}

	public record Usage(@JsonProperty("completion_tokens") int completionTokens,
			@JsonProperty("prompt_tokens") int promptTokens,
			@JsonProperty("total_tokens") int totalTokens) {
	}
}
