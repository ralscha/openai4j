package ch.rasc.openai4j.threads.messages;

import java.util.Map;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.Nullable;

/**
 * Represents a message within a thread.
 */
@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableThreadMessageObject.class)
@Value.Enclosing
public interface ThreadMessageObject {

	/**
	 * The identifier, which can be referenced in API endpoints.
	 */
	String id();

	/**
	 * The object type, which is always thread.message.
	 */
	String object();

	/**
	 * The Unix timestamp (in seconds) for when the message was created.
	 */
	@JsonProperty("created_at")
	int createdAt();

	/**
	 * The thread ID that this message belongs to.
	 */
	@JsonProperty("thread_id")
	String threadId();

	/**
	 * The entity that produced the message. One of user or assistant.
	 */
	String role();

	Content[] content();

	@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
	@JsonSubTypes({ @Type(ImageContent.class), @Type(TextContent.class) })
	interface Content {	
	}

	@Value.Immutable(builder = false)
	@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
	@JsonDeserialize(as = ImmutableThreadMessageObject.ImageContent.class)
	interface ImageContent extends Content {
		String type();
		
		/**
		 * References an image File in the content of a message.
		 */
		@JsonProperty("image_file")
		File imageFile();
	}

	@Value.Immutable(builder = false)
	@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
	@JsonDeserialize(as = ImmutableThreadMessageObject.File.class)
	interface File {
		@JsonProperty("file_id")
		String fileId();
	}

	@Value.Immutable(builder = false)
	@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
	@JsonDeserialize(as = ImmutableThreadMessageObject.TextContent.class)
	interface TextContent extends Content {
		String type();
		/**
		 * The text content that is part of a message.
		 */
		Text text();

		@Value.Immutable(builder = false)
		@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
		@JsonDeserialize(as = ImmutableThreadMessageObject.Text.class)
		interface Text {
			/**
			 * The data that makes up the text.
			 */
			String value();

			Annotation[] annotations();

			@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
			@JsonSubTypes({ @Type(FileCitation.class), @Type(FilePath.class) })
			interface Annotation {
			}

			@Value.Immutable(builder = false)
			@Value.Style(visibility = ImplementationVisibility.PACKAGE,
					allParameters = true)
			@JsonDeserialize(as = ImmutableThreadMessageObject.FileCitation.class)
			interface FileCitation extends Annotation {
				String type();
				/**
				 * The text in the message content that needs to be replaced.
				 */
				String text();

				@JsonProperty("file_citation")
				Citation fileCitation();

				@JsonProperty("start_index")
				int startIndex();

				@JsonProperty("end_index")
				int endIndex();

				@Value.Immutable(builder = false)
				@Value.Style(visibility = ImplementationVisibility.PACKAGE,
						allParameters = true)
				@JsonDeserialize(as = ImmutableThreadMessageObject.Citation.class)
				interface Citation {
					/**
					 * The ID of the specific File the citation is from.
					 */
					@JsonProperty("file_id")
					String fileId();

					/**
					 * The specific quote in the file.
					 */
					String quote();
				}

			}

			@Value.Immutable(builder = false)
			@Value.Style(visibility = ImplementationVisibility.PACKAGE,
					allParameters = true)
			@JsonDeserialize(as = ImmutableThreadMessageObject.FilePath.class)
			interface FilePath extends Annotation {
				String type();
				/**
				 * The text in the message content that needs to be replaced.
				 */
				String text();

				@JsonProperty("file_path")
				File filePath();

				@JsonProperty("start_index")
				int startIndex();

				@JsonProperty("end_index")
				int endIndex();
			}
		}
	}

	/**
	 * If applicable, the ID of the assistant that authored this message.
	 */
	@JsonProperty("assistant_id")
	@Nullable
	String assistantId();

	/**
	 * If applicable, the ID of the run associated with the authoring of this message.
	 */
	@JsonProperty("run_id")
	@Nullable
	String runId();

	/**
	 * A list of file IDs that the assistant should use. Useful for tools like retrieval
	 * and code_interpreter that can access files. A maximum of 10 files can be attached
	 * to a message.
	 */
	@Nullable
	@JsonProperty("file_ids")
	String[] fileIds();

	/**
	 * Set of 16 key-value pairs that can be attached to an object. This can be useful for
	 * storing additional information about the object in a structured format. Keys can be
	 * a maximum of 64 characters long and values can be a maxium of 512 characters long.
	 */
	Map<String, Object> metadata();

}
