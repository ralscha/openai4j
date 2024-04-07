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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import ch.rasc.openai4j.assistants.Tool;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@SuppressWarnings({ "unused", "hiding" })
public class ThreadRunCreateRequest {

	@JsonProperty("assistant_id")
	private final String assistantId;
	private final String model;
	private final String instructions;
	@JsonProperty("additional_instructions")
	private final String additionalInstructions;
	private final List<Tool> tools;
	private final Map<String, Object> metadata;
	private final Double temperature;

	private ThreadRunCreateRequest(Builder builder) {
		if (builder.assistantId == null) {
			throw new IllegalArgumentException("assistantId cannot be null");
		}
		this.assistantId = builder.assistantId;
		this.model = builder.model;
		this.instructions = builder.instructions;
		this.additionalInstructions = builder.additionalInstructions;
		this.tools = builder.tools;
		this.metadata = builder.metadata;
		this.temperature = builder.temperature;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String assistantId;
		private String model;
		private String instructions;
		private String additionalInstructions;
		private List<Tool> tools;
		private Map<String, Object> metadata;
		private Double temperature;

		private Builder() {
		}

		/*
		 * The ID of the assistant to use to execute this run.
		 */
		public Builder assistantId(String assistantId) {
			this.assistantId = assistantId;
			return this;
		}

		/*
		 * The ID of the Model to be used to execute this run. If a value is provided
		 * here, it will override the model associated with the assistant. If not, the
		 * model associated with the assistant will be used.
		 */
		public Builder model(String model) {
			this.model = model;
			return this;
		}

		/*
		 * Override the default system message of the assistant. This is useful for
		 * modifying the behavior on a per-run basis.
		 */
		public Builder instructions(String instructions) {
			this.instructions = instructions;
			return this;
		}

		/*
		 * Appends additional instructions at the end of the instructions for the run.
		 * This is useful for modifying the behavior on a per-run basis without overriding
		 * other instructions.
		 */
		public Builder additionalInstructions(String additionalInstructions) {
			this.additionalInstructions = additionalInstructions;
			return this;
		}

		/*
		 * Override the tools the assistant can use for this run. This is useful for
		 * modifying the behavior on a per-run basis.
		 */
		public Builder tools(List<Tool> tools) {
			this.tools = new ArrayList<>(tools);
			return this;
		}

		/*
		 * Override the tools the assistant can use for this run. This is useful for
		 * modifying the behavior on a per-run basis.
		 */
		public Builder addTools(Tool... tools) {
			if (this.tools == null) {
				this.tools = new ArrayList<>();
			}
			this.tools.addAll(List.of(tools));
			return this;
		}

		/*
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

		public ThreadRunCreateRequest build() {
			return new ThreadRunCreateRequest(this);
		}

		/**
		 * What sampling temperature to use, between 0 and 2. Higher values like 0.8 will
		 * make the output more random, while lower values like 0.2 will make it more
		 * focused and deterministic. Optional. Defaults to 1
		 */
		public Builder temperature(Double temperature) {
			this.temperature = temperature;
			return this;
		}
	}
}
