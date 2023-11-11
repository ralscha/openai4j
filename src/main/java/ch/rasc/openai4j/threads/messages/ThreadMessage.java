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
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ch.rasc.openai4j.Nullable;

/**
 * Represents a message within a thread.
 */
public record ThreadMessage(String id, String object,
		@JsonProperty("created_at") int createdAt,
		@JsonProperty("thread_id") String threadId, String role, Content[] content,
		@JsonProperty("assistant_id") @Nullable String assistantId,
		@JsonProperty("run_id") @Nullable String runId,
		@Nullable @JsonProperty("file_ids") String[] fileIds,
		Map<String, Object> metadata) {

	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
	@JsonSubTypes({ @Type(name = "image_file", value = MessageContentImageFile.class),
			@Type(name = "text", value = MessageContentText.class) })
	interface Content {
	}

	public record MessageContentImageFile(String type,
			@JsonProperty("image_file") File imageFile) implements Content {
	}

	public record File(@JsonProperty("file_id") String fileId) {
	}

	public record MessageContentText(String type, Text text) implements Content {
	}

	public record Text(String value, Annotation[] annotations) {
		@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
		@JsonSubTypes({ @Type(value = FileCitation.class, name = "file_citation"),
				@Type(value = FilePath.class, name = "file_path") })
		interface Annotation {
		}

		public record FileCitation(String type, String text,
				@JsonProperty("file_citation") Citation fileCitation,
				@JsonProperty("start_index") int startIndex,
				@JsonProperty("end_index") int endIndex) implements Annotation {
		}

		public record Citation(@JsonProperty("file_id") String fileId, String quote) {
		}

		public record FilePath(String type, String text,
				@JsonProperty("file_path") File filePath,
				@JsonProperty("start_index") int startIndex,
				@JsonProperty("end_index") int endIndex) implements Annotation {
		}
	}

}
