package ch.rasc.openai4j.threads;

import java.util.Map;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Represents a thread that contains messages.
 */
@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableThreadObject.class)
public interface ThreadObject {

	/**
	 * The identifier, which can be referenced in API endpoints.
	 */
	String id();

	/**
	 * The object type, which is always thread.
	 */
	String object();

	/**
	 * The Unix timestamp (in seconds) for when the thread was created.
	 */
	@JsonProperty("created_at")
	int createdAt();

	/**
	 * Set of 16 key-value pairs that can be attached to an object. This can be useful for
	 * storing additional information about the object in a structured format. Keys can be
	 * a maximum of 64 characters long and values can be a maxium of 512 characters long.
	 */
	Map<String, Object> metadata();

}
