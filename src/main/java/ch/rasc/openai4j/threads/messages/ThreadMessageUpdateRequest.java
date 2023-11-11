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
package ch.rasc.openai4j.threads.messages;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThreadMessageUpdateRequest {
	private final Map<String, Object> metadata;

	ThreadMessageUpdateRequest(Map<String, Object> metadata) {
		this.metadata = Map.copyOf(metadata);
	}

	public static ThreadMessageUpdateRequest of(Map<String, Object> metadata) {
		return new ThreadMessageUpdateRequest(metadata);
	}

	@JsonProperty
	public Map<String, Object> metadata() {
		return this.metadata;
	}
}
