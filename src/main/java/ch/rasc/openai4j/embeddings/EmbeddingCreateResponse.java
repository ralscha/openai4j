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
package ch.rasc.openai4j.embeddings;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmbeddingCreateResponse(String object, List<Embedding> data, String model,
		Usage usage) {

	/**
	 * The object type, which is always "list".
	 */
	@Override
	public String object() {
		return this.object;
	}

	/**
	 * List of embeddings.
	 */
	@Override
	public List<Embedding> data() {
		return this.data;
	}

	/**
	 * Name of the model used.
	 */
	@Override
	public String model() {
		return this.model;
	}

	/**
	 * Information about the API usage.
	 */
	@Override
	public Usage usage() {
		return this.usage;
	}

	public record Usage(@JsonProperty("prompt_tokens") int promptTokens,
			@JsonProperty("total_tokens") int totalTokens) {

		/**
		 * Number of tokens generated by the prompt.
		 */
		@Override
		public int promptTokens() {
			return this.promptTokens;
		}

		/**
		 * Total number of tokens generated.
		 */
		@Override
		public int totalTokens() {
			return this.totalTokens;
		}
	}
}
