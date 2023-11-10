package ch.rasc.openai4j.threads.messages.files;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * A list of files attached to a message.
 */
@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableThreadMessageFile.class)
public interface ThreadMessageFile {

	/*
	 * The identifier, which can be referenced in API endpoints.
	 */
	String id();

	/*
	 * The object type, which is always thread.message.file.
	 */
	String object();

	/*
	 * The Unix timestamp (in seconds) for when the message file was created.
	 */
	@JsonProperty("created_at")
	int createdAt();

	/*
	 * The ID of the message that the File is attached to.
	 */
	@JsonProperty("message_id")
	String messageId();
}
