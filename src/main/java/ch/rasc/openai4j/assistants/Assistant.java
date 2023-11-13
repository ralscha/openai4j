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

import com.fasterxml.jackson.annotation.JsonProperty;

import ch.rasc.openai4j.Nullable;

/**
 * Represents an assistant that can call the model and use tools.
 */
public record Assistant(String id, String object,
		@JsonProperty("created_at") int createdAt, @Nullable String name,
		@Nullable String description, String model, @Nullable String instructions,
		List<Tool> tools, @JsonProperty("file_ids") List<String> fileIds,
		Map<String, Object> metadata) {

	/**
	 * The identifier, which can be referenced in API endpoints.
	 */
	@Override
	public String id() {
		return this.id;
	}

	/**
	 * The object type, which is always assistant.
	 */
	@Override
	public String object() {
		return this.object;
	}

	/**
	 * The Unix timestamp (in seconds) for when the assistant was created.
	 */
	@Override
	public int createdAt() {
		return this.createdAt;
	}

	/**
	 * The name of the assistant. The maximum length is 256 characters.
	 */
	@Override
	public String name() {
		return this.name;
	}

	/**
	 * The description of the assistant. The maximum length is 512 characters.
	 */
	@Override
	public String description() {
		return this.description;
	}

	/**
	 * ID of the model to use. You can use the List models API to see all of your
	 * available models
	 */
	@Override
	public String model() {
		return this.model;
	}

	/**
	 * The system instructions that the assistant uses. The maximum length is 32768
	 * characters.
	 */
	@Override
	public String instructions() {
		return this.instructions;
	}

	/**
	 * A list of tool enabled on the assistant. There can be a maximum of 128 tools per
	 * assistant. Tools can be of types code_interpreter, retrieval, or function.
	 */
	@Override
	public List<Tool> tools() {
		return this.tools;
	}

	/**
	 * A list of file IDs attached to this assistant. There can be a maximum of 20 files
	 * attached to the assistant. Files are ordered by their creation date in ascending
	 * order.
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
}
