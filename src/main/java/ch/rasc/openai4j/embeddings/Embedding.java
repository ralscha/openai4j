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

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonCreator;

import ch.rasc.openai4j.embeddings.Embedding.EmbeddingVector;

/**
 * Represents an embedding vector returned by embedding endpoint.
 */
public record Embedding(int index, EmbeddingVector embedding, String object) {

	public static class EmbeddingVector {
		private final String base64;
		private final double[] doubleArray;

		@JsonCreator
		EmbeddingVector(double[] doubleArray) {
			this.doubleArray = doubleArray;
			this.base64 = null;
		}

		@JsonCreator
		EmbeddingVector(String base64) {
			this.doubleArray = null;
			this.base64 = base64;
		}

		public double[] doubleArray() {
			return this.doubleArray;
		}

		public String base64() {
			return this.base64;
		}

		@Override
		public String toString() {
			return "EmbeddingVector [embeddingBase64=" + this.base64 + ", embedding="
					+ Arrays.toString(this.doubleArray) + "]";
		}

	}

}
