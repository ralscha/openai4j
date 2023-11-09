package ch.rasc.openai4j.threads.runs.steps;

import java.util.Map;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.Nullable;

/**
 * Represents a step in execution of a run.
 */
@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableThreadRunStepObject.class)
@Value.Enclosing
public interface ThreadRunStepObject {

	/*
	 * The identifier of the run step, which can be referenced in API endpoints.
	 */
	String id();

	/*
	 * The object type, which is always `thread.run.step``.
	 */
	String object();

	/*
	 * created_at integer The Unix timestamp (in seconds) for when the run step was
	 * created.
	 */
	@JsonProperty("created_at")
	int createdAt();

	/*
	 * The ID of the assistant associated with the run step.
	 */
	@JsonProperty("assistant_id")
	String assistantId();

	/*
	 * The ID of the thread that was run.
	 */
	@JsonProperty("thread_id")
	String threadId();

	/*
	 * The ID of the run that this run step is a part of.
	 */
	@JsonProperty("run_id")
	String runId();

	enum Type {
		MESSAGE_CREATION("message_creation"), TOOL_CALLS("tool_calls");

		private final String value;

		Type(String value) {
			this.value = value;
		}

		@JsonValue
		public String getValue() {
			return this.value;
		}
	}

	/*
	 * The type of run step
	 */
	Type type();

	enum Status {
		IN_PROGRESS("in_progress"), CANCELLED("cancelled"), FAILED("failed"),
		COMPLETED("completed"), EXPIRED("expired");

		private final String value;

		Status(String value) {
			this.value = value;
		}

		@JsonValue
		public String getValue() {
			return this.value;
		}
	}

	/*
	 * The status of the run step,
	 */
	Status status();

	@JsonProperty("step_details")
	StepDetail stepDetails();

	/**
	 * The last error associated with this run step. Will be null if there are no errors
	 */
	@Nullable
	@JsonProperty("last_error")
	Error lastError();

	@Value.Immutable(builder = false)
	@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE,
			allParameters = true)
	@JsonDeserialize(as = ImmutableThreadRunStepObject.Error.class)
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
	 * The Unix timestamp (in seconds) for when the run step expired. A step is considered
	 * expired if the parent run is expired.
	 */
	@JsonProperty("expired_at")
	@Nullable
	Integer expiredAt();

	/*
	 * cancelled_at integer or null The Unix timestamp (in seconds) for when the run step
	 * was cancelled.
	 */
	@JsonProperty("cancelled_at")
	@Nullable
	Integer cancelledAt();

	/*
	 * failed_at integer or null The Unix timestamp (in seconds) for when the run step
	 * failed.
	 */
	@JsonProperty("failed_at")
	@Nullable
	Integer failedAt();

	/*
	 * completed_at integer or null The Unix timestamp (in seconds) for when the run step
	 * completed.
	 */
	@JsonProperty("completed_at")
	@Nullable
	Integer completedAt();

	/*
	 *
	 * Set of 16 key-value pairs that can be attached to an object. This can be useful for
	 * storing additional information about the object in a structured format. Keys can be
	 * a maximum of 64 characters long and values can be a maxium of 512 characters long.
	 */
	@Nullable
	Map<String, Object> metadata();
}
