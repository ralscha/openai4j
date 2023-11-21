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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@SuppressWarnings({ "unused", "hiding" })
public class ThreadMessageCreateRequest {

	private final String role;
	private final String content;
	@JsonProperty("file_ids")
	private final List<String> fileIds;
	private final Map<String, Object> metadata;

	private ThreadMessageCreateRequest(Builder builder) {
		if (builder.content == null) {
			throw new IllegalArgumentException("content must not be null");
		}
		if (builder.role == null) {
			throw new IllegalArgumentException("role must not be null");
		}
		this.role = builder.role;
		this.content = builder.content;
		this.fileIds = builder.fileIds;
		this.metadata = builder.metadata;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String role;
		private String content;
		private List<String> fileIds;
		private Map<String, Object> metadata;

		private Builder() {
		}

		/**
		 * The role of the entity that is creating the message. Currently only user is
		 * supported.
		 */
		public Builder role(String role) {
			this.role = role;
			return this;
		}

		/**
		 * Set the role of the entity that is creating the message to user
		 */
		public Builder userRole() {
			this.role = "user";
			return this;
		}

		/**
		 * The content of the message.
		 */
		public Builder content(String content) {
			this.content = content;
			return this;
		}

		/**
		 * A list of File IDs that the message should use. There can be a maximum of 10
		 * files attached to a message. Useful for tools like retrieval and
		 * code_interpreter that can access and use files.
		 */
		public Builder fileIds(List<String> fileIds) {
			this.fileIds = new ArrayList<>(fileIds);
			return this;
		}

		/**
		 * A list of File IDs that the message should use. There can be a maximum of 10
		 * files attached to a message. Useful for tools like retrieval and
		 * code_interpreter that can access and use files.
		 */
		public Builder addFileIds(String... fileId) {
			if (this.fileIds == null) {
				this.fileIds = new ArrayList<>();
			}
			this.fileIds.addAll(List.of(fileId));
			return this;
		}

		/**
		 * SSet of 16 key-value pairs that can be attached to an object. This can be
		 * useful for storing additional information about the object in a structured
		 * format. Keys can be a maximum of 64 characters long and values can be a maxium
		 * of 512 characters long.
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

		public ThreadMessageCreateRequest build() {
			return new ThreadMessageCreateRequest(this);
		}
	}
}
