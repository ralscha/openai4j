package ch.rasc.openai4j.threads.runs.steps;

import java.util.Map;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.Nullable;

/**
 * Represents a step in execution of a run.
 */
@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableThreadRunStep.class)
@Value.Enclosing
public interface ThreadRunStep {

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

	/*
	 * The type of run step
	 */
	ThreadRunStepType type();

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

	/*
	 * The status of the run step,
	 */
	ThreadRunStepStatus status();

	@JsonProperty("step_details")
	StepDetail stepDetails();

	@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
	@JsonSubTypes({ @Type(MessageCreationStepDetails.class),
			@Type(ToolCallsStepDetails.class) })
	public interface StepDetail {
	}

	@Value.Immutable(builder = false)
	@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
	@JsonDeserialize(as = ImmutableThreadRunStep.MessageCreationStepDetails.class)
	public interface MessageCreationStepDetails extends StepDetail {
		String type();

		/**
		 * Details of the message creation by the run step.
		 */
		MessageCreation messageCreation();

		@Value.Immutable(builder = false)
		@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
		@JsonDeserialize(as = ImmutableThreadRunStep.MessageCreation.class)
		public interface MessageCreation {
			/**
			 * The ID of the message that was created by this run step.
			 */
			@JsonProperty("message_id")
			String messageId();
		}
	}

	@Value.Immutable(builder = false)
	@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
	@JsonDeserialize(as = ImmutableThreadRunStep.ToolCallsStepDetails.class)
	public interface ToolCallsStepDetails extends StepDetail {
		String type();

		@JsonProperty("tool_calls")
		ToolCall[] toolCalls();

		@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
		@JsonSubTypes({ @Type(CodeToolCall.class), @Type(RetrievalToolCall.class),
				@Type(FunctionToolCall.class) })
		public interface ToolCall {
		}

		@Value.Immutable(builder = false)
		@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
		@JsonDeserialize(as = ImmutableThreadRunStep.CodeToolCall.class)
		public interface CodeToolCall extends ToolCall {
			/**
			 * The ID of the tool call.
			 */
			String id();

			/**
			 * The type of tool call.
			 */
			String type();

			/**
			 * The Code Interpreter tool call definition.
			 */
			@JsonProperty("code_interpreter")
			CodeInterpreter codeInterpreter();

			@Value.Immutable(builder = false)
			@Value.Style(visibility = ImplementationVisibility.PACKAGE,
					allParameters = true)
			@JsonDeserialize(as = ImmutableThreadRunStep.CodeInterpreter.class)
			public interface CodeInterpreter {
				/**
				 * The input to the Code Interpreter tool call.
				 */
				String input();

				/**
				 * The outputs from the Code Interpreter tool call. Code Interpreter can
				 * output one or more items, including text (logs) or images (image). Each
				 * of these are represented by a different object type.
				 */
				CodeInterpreterOutput[] outputs();

				@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
				@JsonSubTypes({ @Type(ImageCodeInterpreterOutput.class),
						@Type(LogCodeInterpreterOutput.class) })
				public interface CodeInterpreterOutput {
				}

				@Value.Immutable(builder = false)
				@Value.Style(visibility = ImplementationVisibility.PACKAGE,
						allParameters = true)
				@JsonDeserialize(
						as = ImmutableThreadRunStep.ImageCodeInterpreterOutput.class)
				public interface ImageCodeInterpreterOutput
						extends CodeInterpreterOutput {
					String type();

					Image image();

					@Value.Immutable(builder = false)
					@Value.Style(visibility = ImplementationVisibility.PACKAGE,
							allParameters = true)
					@JsonDeserialize(as = ImmutableThreadRunStep.Image.class)
					public interface Image {
						/**
						 * The file ID of the image.
						 */
						@JsonProperty("file_id")
						String fileId();
					}
				}

				@Value.Immutable(builder = false)
				@Value.Style(visibility = ImplementationVisibility.PACKAGE,
						allParameters = true)
				@JsonDeserialize(
						as = ImmutableThreadRunStep.LogCodeInterpreterOutput.class)
				public interface LogCodeInterpreterOutput extends CodeInterpreterOutput {
					String type();

					/**
					 * The text output from the Code Interpreter tool call.
					 */
					String logs();
				}
			}

		}

		@Value.Immutable(builder = false)
		@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
		@JsonDeserialize(as = ImmutableThreadRunStep.RetrievalToolCall.class)
		public interface RetrievalToolCall extends ToolCall {
			/**
			 * The ID of the tool call object.
			 */
			String id();

			/**
			 * The type of tool call.
			 */
			String type();

			/**
			 * For now, this is always going to be an empty object.
			 */
			Map<String, Object> retrieval();
		}

		@Value.Immutable(builder = false)
		@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
		@JsonDeserialize(as = ImmutableThreadRunStep.FunctionToolCall.class)
		public interface FunctionToolCall extends ToolCall {
			/**
			 * The ID of the tool call object.
			 */
			String id();

			/**
			 * The type of tool call.
			 */
			String type();

			/**
			 * The definition of the function that was called.
			 */
			Function function();

			@Value.Immutable(builder = false)
			@Value.Style(visibility = ImplementationVisibility.PACKAGE,
					allParameters = true)
			@JsonDeserialize(as = ImmutableThreadRunStep.Function.class)
			public interface Function {
				/**
				 * The name of the function.
				 */
				String name();

				/**
				 * The arguments passed to the function.
				 */
				String arguments();

				/**
				 * The output of the function. This will be null if the outputs have not
				 * been submitted yet.
				 */
				@Nullable
				String output();
			}
		}
	}

	/**
	 * The last error associated with this run step. Will be null if there are no errors
	 */
	@Nullable
	@JsonProperty("last_error")
	Error lastError();

	@Value.Immutable(builder = false)
	@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE,
			allParameters = true)
	@JsonDeserialize(as = ImmutableThreadRunStep.Error.class)
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
