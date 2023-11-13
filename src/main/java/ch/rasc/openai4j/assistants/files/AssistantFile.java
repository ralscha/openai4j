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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A list of Files attached to an assistant.
 */
public record AssistantFile(String id, String object,
		@JsonProperty("created_at") int createdAt,
		@JsonProperty("assistant_id") String assistantId) {

	/**
	 * The identifier, which can be referenced in API endpoints.
	 */
	@Override
	public String id() {
		return this.id;
	}

	/**
	 * The object type, which is always assistant.file.
	 */
	@Override
	public String object() {
		return this.object;
	}

	/**
	 * The Unix timestamp (in seconds) for when the assistant file was created.
	 */
	@Override
	public int createdAt() {
		return this.createdAt;
	}

	/**
	 * The assistant ID that the file is attached to.
	 */
	@Override
	public String assistantId() {
		return this.assistantId;
	}

}
