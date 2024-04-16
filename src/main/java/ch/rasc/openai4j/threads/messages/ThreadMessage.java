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

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Represents a message within a thread.
 */
public record ThreadMessage(String id, String object,
		@JsonProperty("created_at") int createdAt,
		@JsonProperty("thread_id") String threadId, String status,
		@JsonProperty("incomplete_details") IncompleteDetail incompleteDetails,
		@JsonProperty("completed_at") Integer completedAt,
		@JsonProperty("incomplete_at") Integer incompleteAt, String role,
		List<Content> content, @JsonProperty("assistant_id") String assistantId,
		@JsonProperty("run_id") String runId,
		@JsonProperty("file_ids") List<String> fileIds, Map<String, Object> metadata) {

	/**
	 * The identifier, which can be referenced in API endpoints.
	 */
	@Override
	public String id() {
		return this.id;
	}

	/**
	 * The object type, which is always thread.message.
	 */
	@Override
	public String object() {
		return this.object;
	}

	/**
	 * The Unix timestamp (in seconds) for when the message was created.
	 */
	@Override
	public int createdAt() {
		return this.createdAt;
	}

	/**
	 * The thread ID that this message belongs to.
	 */
	@Override
	public String threadId() {
		return this.threadId;
	}

	/**
	 * The status of the message, which can be either in_progress, incomplete, or
	 * completed.
	 */
	@Override
	public String status() {
		return this.status;
	}

	/**
	 * On an incomplete message, details about why the message is incomplete.
	 */
	@Override
	public IncompleteDetail incompleteDetails() {
		return this.incompleteDetails;
	}

	/**
	 * The Unix timestamp (in seconds) for when the message was completed.
	 */
	@Override
	public Integer completedAt() {
		return this.completedAt;
	}

	/**
	 * The Unix timestamp (in seconds) for when the message was marked as incomplete.
	 */
	@Override
	public Integer incompleteAt() {
		return this.incompleteAt;
	}

	/**
	 * The entity that produced the message. One of user or assistant.
	 */
	@Override
	public String role() {
		return this.role;
	}

	/**
	 * The content of the message in array of text and/or images.
	 */
	@Override
	public List<Content> content() {
		return this.content;
	}

	/**
	 * If applicable, the ID of the assistant that authored this message.
	 */
	@Override
	public String assistantId() {
		return this.assistantId;
	}

	/**
	 * If applicable, the ID of the run associated with the authoring of this message.
	 */
	@Override
	public String runId() {
		return this.runId;
	}

	/**
	 * A list of file IDs that the assistant should use. Useful for tools like retrieval
	 * and code_interpreter that can access files. A maximum of 10 files can be attached
	 * to a message.
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

	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
	@JsonSubTypes({ @Type(name = "image_file", value = MessageContentImageFile.class),
			@Type(name = "text", value = MessageContentText.class) })
	public interface Content {
	}

	public record IncompleteDetail(String reason) {
		/**
		 * The reason the message is incomplete.
		 */
		@Override
		public String reason() {
			return this.reason;
		}
	}

	public record MessageContentImageFile(String type,
			@JsonProperty("image_file") File imageFile) implements Content {

		/**
		 * Always image_file.
		 */
		@Override
		public String type() {
			return this.type;
		}

		@Override
		public File imageFile() {
			return this.imageFile;
		}
	}

	public record File(@JsonProperty("file_id") String fileId) {
		/**
		 * The File ID of the image in the message content.
		 */
		@Override
		public String fileId() {
			return this.fileId;
		}
	}

	public record MessageContentText(String type, Text text) implements Content {
		/**
		 * Always text.
		 */
		@Override
		public String type() {
			return this.type;
		}

		@Override
		public Text text() {
			return this.text;
		}
	}

	public record Text(String value, List<Annotation> annotations) {

		/**
		 * The data that makes up the text.
		 */
		@Override
		public String value() {
			return this.value;
		}

		@Override
		public List<Annotation> annotations() {
			return this.annotations;
		}

		@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
		@JsonSubTypes({ @Type(value = FileCitation.class, name = "file_citation"),
				@Type(value = FilePath.class, name = "file_path") })
		public interface Annotation {
		}

		/**
		 * A citation within the message that points to a specific quote from a specific
		 * File associated with the assistant or the message. Generated when the assistant
		 * uses the "retrieval" tool to search files.
		 */
		public record FileCitation(String type, String text,
				@JsonProperty("file_citation") Citation fileCitation,
				@JsonProperty("start_index") int startIndex,
				@JsonProperty("end_index") int endIndex) implements Annotation {

			/**
			 * Always file_citation.
			 */
			@Override
			public String type() {
				return this.type;
			}

			/**
			 * The text in the message content that needs to be replaced.
			 */
			@Override
			public String text() {
				return this.text;
			}

			@Override
			public Citation fileCitation() {
				return this.fileCitation;
			}

			@Override
			public int startIndex() {
				return this.startIndex;
			}

			@Override
			public int endIndex() {
				return this.endIndex;
			}

		}

		public record Citation(@JsonProperty("file_id") String fileId, String quote) {
			/**
			 * The ID of the specific File the citation is from.
			 */
			@Override
			public String fileId() {
				return this.fileId;
			}

			/**
			 * The specific quote in the file.
			 */
			@Override
			public String quote() {
				return this.quote;
			}
		}

		public record FilePath(String type, String text,
				@JsonProperty("file_path") File filePath,
				@JsonProperty("start_index") int startIndex,
				@JsonProperty("end_index") int endIndex) implements Annotation {
			/**
			 * Always file_path.
			 */
			@Override
			public String type() {
				return this.type;
			}

			/**
			 * The text in the message content that needs to be replaced.
			 */
			@Override
			public String text() {
				return this.text;
			}

			@Override
			public File filePath() {
				return this.filePath;
			}

			@Override
			public int startIndex() {
				return this.startIndex;
			}

			@Override
			public int endIndex() {
				return this.endIndex;
			}
		}
	}

}
