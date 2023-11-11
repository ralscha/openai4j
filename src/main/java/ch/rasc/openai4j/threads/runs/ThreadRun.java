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
package ch.rasc.openai4j.threads.runs;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import ch.rasc.openai4j.Nullable;
import ch.rasc.openai4j.assistants.Tool;
import ch.rasc.openai4j.common.Error;
import ch.rasc.openai4j.common.FunctionArguments;

/**
 * Represents an execution run on a thread.
 */
public record ThreadRun(String id, String object,
		@JsonProperty("created_at") int createdAt,
		@JsonProperty("thread_id") String threadId,
		@JsonProperty("assistant_id") String assistantId, Status status,
		@Nullable @JsonProperty("required_action") RequiredActionFunctionToolCall requiredAction,
		@Nullable @JsonProperty("last_error") Error lastError,
		@JsonProperty("expires_at") int expiresAt,
		@Nullable @JsonProperty("started_at") Integer startedAt,
		@Nullable @JsonProperty("cancelled_at") Integer cancelledAt,
		@Nullable @JsonProperty("failed_at") Integer failedAt,
		@Nullable @JsonProperty("completed_at") Integer completedAt, String model,
		String instructions, Tool[] tools, @JsonProperty("file_ids") String[] fileIds,
		@JsonProperty("metadata") Map<String, Object> metadata) {

	public enum Status {
		QUEUED("queued"), IN_PROGRESS("in_progress"), REQUIRES_ACTION("requires_action"),
		CANCELLING("cancelling"), CANCELLED("cancelled"), FAILED("failed"),
		COMPLETED("completed"), EXPIRED("expired");

		private final String value;

		Status(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}

		public boolean isTerminal() {
			return this == REQUIRES_ACTION || this == CANCELLED || this == FAILED
					|| this == COMPLETED || this == EXPIRED;
		}
	}

	public record RequiredActionFunctionToolCall(String type,
			SubmitToolOutputs submitToolOutputs) {
		public record SubmitToolOutputs(
				@JsonProperty("tool_calls") ToolCall[] toolCalls) {
			public record ToolCall(String id, String type, FunctionArguments function) {
			}
		}
	}

}
