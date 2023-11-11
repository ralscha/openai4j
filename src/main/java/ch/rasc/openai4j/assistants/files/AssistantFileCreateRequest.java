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
package ch.rasc.openai4j.assistants.files;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE)
@JsonSerialize(as = ImmutableAssistantFileCreateRequest.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface AssistantFileCreateRequest {

	static Builder builder() {
		return new Builder();
	}

	/**
	 * A File ID (with purpose="assistants") that the assistant should use. Useful for
	 * tools like retrieval and code_interpreter that can access files.
	 */
	@JsonProperty("file_id")
	String fileId();

	final class Builder extends ImmutableAssistantFileCreateRequest.Builder {
	}
}
