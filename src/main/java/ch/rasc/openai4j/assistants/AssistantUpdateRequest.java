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
package ch.rasc.openai4j.assistants;

import java.util.List;
import java.util.Map;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonSerialize(as = ImmutableAssistantUpdateRequest.class)
@JsonInclude(Include.NON_EMPTY)
public interface AssistantUpdateRequest {

	/*
	 * ID of the model to use. You can use the List models API to see all of your
	 * available models, or see our Model overview for descriptions of them.
	 */
	@Nullable
	String model();

	/*
	 * The name of the assistant. The maximum length is 256 characters.
	 */
	@Nullable
	String name();

	/*
	 * The description of the assistant. The maximum length is 512 characters.
	 */
	@Nullable
	String description();

	/*
	 * The system instructions that the assistant uses. The maximum length is 32768
	 * characters.
	 */
	@Nullable
	String instructions();

	/*
	 * A list of tool enabled on the assistant. There can be a maximum of 128 tools per
	 * assistant. Tools can be of types code_interpreter, retrieval, or function.
	 */
	@Nullable
	List<Tool> tools();

	/*
	 * A list of file IDs attached to this assistant. There can be a maximum of 20 files
	 * attached to the assistant. Files are ordered by their creation date in ascending
	 * order.
	 */
	@Nullable
	@JsonProperty("file_ids")
	List<String> fileIds();

	/*
	 * Set of 16 key-value pairs that can be attached to an object. This can be useful for
	 * storing additional information about the object in a structured format. Keys can be
	 * a maximum of 64 characters long and values can be a maxium of 512 characters long.
	 */
	@Nullable
	Map<String, Object> metadata();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableAssistantUpdateRequest.Builder {
	}

}
