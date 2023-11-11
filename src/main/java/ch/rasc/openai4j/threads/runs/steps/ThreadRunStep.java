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
package ch.rasc.openai4j.threads.runs.steps;

import ch.rasc.openai4j.Nullable;
import ch.rasc.openai4j.common.Error;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;

/**
 * Represents a step in execution of a run.
 */
public record ThreadRunStep(String id, String object,
		@JsonProperty("created_at") int createdAt,
		@JsonProperty("assistant_id") String assistantId,
		@JsonProperty("thread_id") String threadId, @JsonProperty("run_id") String runId,
		ThreadRunStepType type, ThreadRunStepStatus status,
		@JsonProperty("step_details") StepDetail stepDetails,
		@Nullable @JsonProperty("last_error") Error lastError,
		@Nullable @JsonProperty("expired_at") Integer expiredAt,
		@Nullable @JsonProperty("cancelled_at") Integer cancelledAt,
		@Nullable @JsonProperty("failed_at") Integer failedAt,
		@Nullable @JsonProperty("completed_at") Integer completedAt,
		@Nullable Map<String, Object> metadata) {

	enum ThreadRunStepType {
		MESSAGE_CREATION("message_creation"), TOOL_CALLS("tool_calls");

		private final String value;

		ThreadRunStepType(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	enum ThreadRunStepStatus {
		IN_PROGRESS("in_progress"), CANCELLED("cancelled"), FAILED("failed"),
		COMPLETED("completed"), EXPIRED("expired");

		private final String value;

		ThreadRunStepStatus(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
	@JsonSubTypes({
			@Type(value = MessageCreationStepDetails.class, name = "message_creation"),
			@Type(value = ToolCallsStepDetails.class, name = "tool_calls") })
	interface StepDetail {
	}

	record MessageCreationStepDetails(String type,
			@JsonProperty("message_creation") MessageCreation messageCreation)
			implements StepDetail {
		record MessageCreation(@JsonProperty("message_id") String messageId) {
		}
	}

	record ToolCallsStepDetails(String type,
			@JsonProperty("tool_calls") ToolCall[] toolCalls) implements StepDetail {

		@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
		@JsonSubTypes({ @Type(value = CodeToolCall.class, name = "code_interpreter"),
				@Type(value = RetrievalToolCall.class, name = "retrieval"),
				@Type(value = FunctionToolCall.class, name = "function") })
		interface ToolCall {
		}

		record CodeToolCall(String id, String type,
				@JsonProperty("code_interpreter") CodeInterpreter codeInterpreter)
				implements ToolCall {
			record CodeInterpreter(String input, CodeInterpreterOutput[] outputs) {

				@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type",
						visible = true)
				@JsonSubTypes({
						@Type(value = ImageCodeInterpreterOutput.class, name = "image"),
						@Type(value = LogCodeInterpreterOutput.class, name = "logs") })
				interface CodeInterpreterOutput {
				}

				record ImageCodeInterpreterOutput(String type, Image image)
						implements CodeInterpreterOutput {
				}

				record Image(@JsonProperty("file_id") String fileId) {
				}

				record LogCodeInterpreterOutput(String type, String logs)
						implements CodeInterpreterOutput {
				}
			}
		}

		record RetrievalToolCall(String id, String type, Map<String, Object> retrieval)
				implements ToolCall {
		}

		record FunctionToolCall(String id, String type, Function function)
				implements ToolCall {
			record Function(String name, String arguments, @Nullable String output) {
			}
		}

	}

}
