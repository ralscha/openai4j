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

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE, depluralize = true)
@JsonSerialize(as = ImmutableThreadRunSubmitToolOutputsRequest.class)
@JsonInclude(Include.NON_EMPTY)
@Value.Enclosing
public interface ThreadRunSubmitToolOutputsRequest {

	static Builder builder() {
		return new Builder();
	}

	@JsonProperty("tool_outputs")
	List<ToolOutput> toolOutputs();

	@Value.Immutable
	@Value.Style(visibility = ImplementationVisibility.PACKAGE, depluralize = true)
	@JsonSerialize(as = ImmutableThreadRunSubmitToolOutputsRequest.ToolOutput.class)
	@JsonInclude(Include.NON_EMPTY)
	interface ToolOutput {

		static Builder builder() {
			return new Builder();
		}

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

		final class Builder
				extends ImmutableThreadRunSubmitToolOutputsRequest.ToolOutput.Builder {
		}
	}

	final class Builder extends ImmutableThreadRunSubmitToolOutputsRequest.Builder {
	}
}
