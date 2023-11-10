package ch.rasc.openai4j.threads.runs;

import java.util.List;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonSerialize(as = ImmutableThreadRunSubmitToolOutputsRequest.class)
@JsonInclude(Include.NON_EMPTY)
@Value.Enclosing
public interface ThreadRunSubmitToolOutputsRequest {

	@JsonProperty("tool_outputs")
	List<ToolOutput> toolOutputs();

	@Value.Immutable
	@Value.Style(visibility = ImplementationVisibility.PACKAGE)
	@JsonSerialize(as = ImmutableThreadRunSubmitToolOutputsRequest.ToolOutput.class)
	@JsonInclude(Include.NON_EMPTY)
	interface ToolOutput {

		/*
		 * The ID of the tool call in the required_action object within the run object the
		 * output is being submitted for.
		 */
		@JsonProperty("tool_call_id")
		@Nullable
		String toolCallId();

		/*
		 * The output of the tool call to be submitted to continue the run.
		 */
		@Nullable
		String output();

		static Builder builder() {
			return new Builder();
		}

		final class Builder
				extends ImmutableThreadRunSubmitToolOutputsRequest.ToolOutput.Builder {
		}
	}

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableThreadRunSubmitToolOutputsRequest.Builder {
	}
}
