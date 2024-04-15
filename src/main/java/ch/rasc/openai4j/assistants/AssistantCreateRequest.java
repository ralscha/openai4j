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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@SuppressWarnings({ "unused", "hiding" })
public class AssistantCreateRequest {

	private final String model;
	private final String name;
	private final String description;
	private final String instructions;
	private final List<Tool> tools;
	@JsonProperty("file_ids")
	private final List<String> fileIds;
	private final Map<String, Object> metadata;

	private AssistantCreateRequest(Builder builder) {
		if (builder.model == null || builder.model.isBlank()) {
			throw new IllegalArgumentException("model must not be null or empty");
		}
		this.model = builder.model;
		this.name = builder.name;
		this.description = builder.description;
		this.instructions = builder.instructions;
		this.tools = builder.tools;
		this.fileIds = builder.fileIds;
		this.metadata = builder.metadata;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String model;
		private String name;
		private String description;
		private String instructions;
		private List<Tool> tools;
		private List<String> fileIds;
		private Map<String, Object> metadata;

		private Builder() {
		}

		/**
		 * ID of the model to use. You can use the List models API to see all of your
		 * available models.
		 */
		public Builder model(String model) {
			this.model = model;
			return this;
		}

		/**
		 * The name of the assistant. The maximum length is 256 characters.
		 */
		public Builder name(String name) {
			this.name = name;
			return this;
		}

		/**
		 * The description of the assistant. The maximum length is 512 characters.
		 */
		public Builder description(String description) {
			this.description = description;
			return this;
		}

		/**
		 * The system instructions that the assistant uses. The maximum length is 32768
		 * characters.
		 */
		public Builder instructions(String instructions) {
			this.instructions = instructions;
			return this;
		}

		/**
		 * A list of tool enabled on the assistant. There can be a maximum of 128 tools
		 * per assistant. Tools can be of types code_interpreter, retrieval, or function.
		 */
		public Builder tools(List<Tool> tools) {
			this.tools = new ArrayList<>(tools);
			return this;
		}

		/**
		 * Add a tool to the list of tools enabled on the assistant
		 */
		public Builder addTools(Tool... tools) {
			if (tools == null || tools.length == 0) {
				return this;
			}
			if (this.tools == null) {
				this.tools = new ArrayList<>();
			}
			this.tools.addAll(List.of(tools));
			return this;
		}

		/**
		 * A list of file IDs attached to this assistant. There can be a maximum of 20
		 * files attached to the assistant. Files are ordered by their creation date in
		 * ascending order.
		 */
		public Builder fileIds(List<String> fileIds) {
			this.fileIds = new ArrayList<>(fileIds);
			return this;
		}

		/**
		 * Add a file IDs to the list of file IDs attached to this assistant
		 */
		public Builder addFileIds(String... fileIds) {
			if (fileIds == null || fileIds.length == 0) {
				return this;
			}

			if (this.fileIds == null) {
				this.fileIds = new ArrayList<>();
			}
			this.fileIds.addAll(List.of(fileIds));
			return this;
		}

		/**
		 * Set of 16 key-value pairs that can be attached to an object. This can be useful
		 * for storing additional information about the object in a structured format.
		 * Keys can be a maximum of 64 characters long and values can be a maxium of 512
		 * characters long.
		 */
		public Builder metadata(Map<String, Object> metadata) {
			this.metadata = new HashMap<>(metadata);
			return this;
		}

		/**
		 * Add a key-value pair to the metadata
		 */
		public Builder putMetadata(String key, Object value) {
			if (this.metadata == null) {
				this.metadata = new HashMap<>();
			}
			this.metadata.put(key, value);
			return this;
		}

		public AssistantCreateRequest build() {
			return new AssistantCreateRequest(this);
		}
	}
}
