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

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import ch.rasc.openai4j.assistants.Tool;
import ch.rasc.openai4j.common.Error;
import ch.rasc.openai4j.common.ToolCall;

/**
 * Represents an execution run on a thread.
 */
public record ThreadRun(String id, String object,
		@JsonProperty("created_at") int createdAt,
		@JsonProperty("thread_id") String threadId,
		@JsonProperty("assistant_id") String assistantId, Status status,
		@JsonProperty("required_action") RequiredActionFunctionToolCall requiredAction,
		@JsonProperty("last_error") Error lastError,
		@JsonProperty("expires_at") int expiresAt,
		@JsonProperty("started_at") Integer startedAt,
		@JsonProperty("cancelled_at") Integer cancelledAt,
		@JsonProperty("failed_at") Integer failedAt,
		@JsonProperty("completed_at") Integer completedAt, String model,
		String instructions, List<Tool> tools,
		@JsonProperty("file_ids") List<String> fileIds,
		@JsonProperty("metadata") Map<String, Object> metadata) {

	/**
	 * The identifier, which can be referenced in API endpoints.
	 */
	@Override
	public String id() {
		return this.id;
	}

	/**
	 * The object type, which is always thread.run.
	 */
	@Override
	public String object() {
		return this.object;
	}

	/**
	 * The Unix timestamp (in seconds) for when the run was created.
	 */
	@Override
	public int createdAt() {
		return this.createdAt;
	}

	/**
	 * The ID of the thread that was executed on as a part of this run.
	 */
	@Override
	public String threadId() {
		return this.threadId;
	}

	/**
	 * The ID of the assistant used for execution of this run.
	 */
	@Override
	public String assistantId() {
		return this.assistantId;
	}

	/**
	 * The status of the run.
	 */
	@Override
	public Status status() {
		return this.status;
	}

	/**
	 * Details on the action required to continue the run. Will be null if no action is
	 * required.
	 */
	@Override
	public RequiredActionFunctionToolCall requiredAction() {
		return this.requiredAction;
	}

	/**
	 * The last error associated with this run. Will be null if there are no errors.
	 */
	@Override
	public Error lastError() {
		return this.lastError;
	}

	/**
	 * The Unix timestamp (in seconds) for when the run will expire.
	 */
	@Override
	public int expiresAt() {
		return this.expiresAt;
	}

	/**
	 * The Unix timestamp (in seconds) for when the run was started.
	 */
	@Override
	public Integer startedAt() {
		return this.startedAt;
	}

	/**
	 * The Unix timestamp (in seconds) for when the run was cancelled.
	 */
	@Override
	public Integer cancelledAt() {
		return this.cancelledAt;
	}

	/**
	 * The Unix timestamp (in seconds) for when the run failed.
	 */
	@Override
	public Integer failedAt() {
		return this.failedAt;
	}

	/**
	 * The Unix timestamp (in seconds) for when the run was completed.
	 */
	@Override
	public Integer completedAt() {
		return this.completedAt;
	}

	/**
	 * The model that the assistant used for this run.
	 */
	@Override
	public String model() {
		return this.model;
	}

	/**
	 * The instructions that the assistant used for this run.
	 */
	@Override
	public String instructions() {
		return this.instructions;
	}

	/**
	 * The list of tools that the assistant used for this run.
	 */
	@Override
	public List<Tool> tools() {
		return this.tools;
	}

	/**
	 * The list of File IDs the assistant used for this run.
	 */
	@Override
	public List<String> fileIds() {
		return this.fileIds;
	}

	/**
	 * Set of 16 key-value pairs that can be attached to an object. This can be useful for
	 * storing additional information about the object in a structured format. Keys can be
	 * a maximum of 64 characters long and values can be a maxium of 512 characters long.
	 */
	@Override
	public Map<String, Object> metadata() {
		return this.metadata;
	}

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
			@JsonProperty("submit_tool_outputs") SubmitToolOutputs submitToolOutputs) {

		/**
		 * For now, this is always submit_tool_outputs.
		 */
		@Override
		public String type() {
			return this.type;
		}

		/**
		 * Details on the tool outputs needed for this run to continue.
		 */
		@Override
		public SubmitToolOutputs submitToolOutputs() {
			return this.submitToolOutputs;
		}

		public record SubmitToolOutputs(
				@JsonProperty("tool_calls") List<ToolCall> toolCalls) {

			/**
			 * A list of the relevant tool calls.
			 */
			@Override
			public List<ToolCall> toolCalls() {
				return this.toolCalls;
			}
		}
	}

}
