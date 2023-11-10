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

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.Nullable;
import ch.rasc.openai4j.assistants.Tool;

/**
 * Represents an execution run on a thread.
 */
@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableThreadRun.class)
@Value.Enclosing
public interface ThreadRun {

	/*
	 * The identifier, which can be referenced in API endpoints.
	 */
	String id();

	/*
	 * The object type, which is always thread.run.
	 */
	String object();

	/*
	 * The Unix timestamp (in seconds) for when the run was created.
	 */
	@JsonProperty("created_at")
	int createdAt();

	/*
	 * The ID of the thread that was executed on as a part of this run.
	 */
	@JsonProperty("thread_id")
	String threadId();

	/*
	 * The ID of the assistant used for execution of this run.
	 */
	@JsonProperty("assistant_id")
	String assistantId();

	enum Status {
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
	}

	/*
	 * The status of the run
	 */
	Status status();

	/**
	 * Details on the action required to continue the run. Will be null if no action is
	 * required.
	 */
	@Nullable
	@JsonProperty("required_action")
	RequiredActionFunctionToolCall requiredAction();

	@Value.Immutable(builder = false)
	@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE,
			allParameters = true)
	@JsonDeserialize(as = ImmutableThreadRun.RequiredActionFunctionToolCall.class)
	interface RequiredActionFunctionToolCall {
		/**
		 * For now, this is always submit_tool_outputs.
		 */
		String type();

		@JsonProperty("submit_tool_outputs")
		SubmitToolOutputs submitToolOutputs();

		@Value.Immutable(builder = false)
		@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE,
				allParameters = true)
		@JsonDeserialize(as = ImmutableThreadRun.SubmitToolOutputs.class)
		interface SubmitToolOutputs {
			@JsonProperty("tool_calls")
			ToolCall[] toolCalls();

			@Value.Immutable(builder = false)
			@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE,
					allParameters = true)
			@JsonDeserialize(as = ImmutableThreadRun.ToolCall.class)
			interface ToolCall {
				/**
				 * The ID of the tool call. This ID must be referenced when you submit the
				 * tool outputs in using the Submit tool outputs to run endpoint.
				 */
				String id();

				/**
				 * The type of tool call the output is required for. For now, this is
				 * always function
				 */
				String type();

				Function function();

				@Value.Immutable(builder = false)
				@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE,
						allParameters = true)
				@JsonDeserialize(as = ImmutableThreadRun.Function.class)
				interface Function {
					/**
					 * The name of the function.
					 */
					String name();

					/**
					 * The arguments that the model expects you to pass to the function.
					 */
					String arguments();
				}
			}
		}
	}

	/**
	 * The last error associated with this run. Will be null if there are no errors.
	 */
	@Nullable
	@JsonProperty("last_error")
	Error lastError();

	@Value.Immutable(builder = false)
	@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE,
			allParameters = true)
	@JsonDeserialize(as = ImmutableThreadRun.Error.class)
	interface Error {
		/**
		 * One of server_error or rate_limit_exceeded.
		 */
		String code();

		/**
		 * A human-readable description of the error.
		 */
		String message();
	}

	/*
	 * The Unix timestamp (in seconds) for when the run will expire.
	 */
	@JsonProperty("expires_at")
	int expiresAt();

	/*
	 * The Unix timestamp (in seconds) for when the run was started.
	 */
	@Nullable
	@JsonProperty("started_at")
	Integer startedAt();

	/*
	 * The Unix timestamp (in seconds) for when the run was cancelled.
	 */
	@Nullable
	@JsonProperty("cancelled_at")
	Integer cancelledAt();

	/*
	 * The Unix timestamp (in seconds) for when the run failed.
	 */
	@Nullable
	@JsonProperty("failed_at")
	Integer failedAt();

	/*
	 * The Unix timestamp (in seconds) for when the run was completed.
	 */
	@Nullable
	@JsonProperty("completed_at")
	Integer completedAt();

	/*
	 * The model that the assistant used for this run.
	 */
	String model();

	/*
	 * The instructions that the assistant used for this run.
	 */
	String instructions();

	/*
	 * The list of tools that the assistant used for this run.
	 */
	Tool[] tools();

	/*
	 * The list of File IDs the assistant used for this run.
	 */
	@JsonProperty("file_ids")
	String[] fileIds();

	/*
	 * Set of 16 key-value pairs that can be attached to an object. This can be useful for
	 * storing additional information about the object in a structured format. Keys can be
	 * a maximum of 64 characters long and values can be a maxium of 512 characters long.
	 */
	@JsonProperty("metadata")
	Map<String, Object> metadata();
}
