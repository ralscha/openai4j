package ch.rasc.openai4j.assistants;

import ch.rasc.openai4j.Nullable;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.List;
import java.util.Map;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonSerialize(as = ImmutableAssistantCreateRequest.class)
@JsonInclude(Include.NON_EMPTY)
public interface AssistantCreateRequest {

	/*
	 * ID of the model to use. You can use the List models API to see all of your
	 * available models, or see our Model overview for descriptions of them.
	 */
	String model();

	/*
	 * The name of the assistant. The maximum length is 256 characters.
	 */
	@Nullable
	String name();

	/*
	 * The description of the assistant. The maximum length is 512 characters.
	 */
	@Nullable
	String description();

	/*
	 * The system instructions that the assistant uses. The maximum length is 32768
	 * characters.
	 */
	@Nullable
	String instructions();

	/*
	 * A list of tool enabled on the assistant. There can be a maximum of 128 tools per
	 * assistant. Tools can be of types code_interpreter, retrieval, or function.
	 */
	@Nullable
	List<Tool> tools();

	/*
	 * A list of file IDs attached to this assistant. There can be a maximum of 20 files
	 * attached to the assistant. Files are ordered by their creation date in ascending
	 * order.
	 */
	@Nullable
	@JsonProperty("file_ids")
	List<String> fileIds();

	/*
	 * Set of 16 key-value pairs that can be attached to an object. This can be useful for
	 * storing additional information about the object in a structured format. Keys can be
	 * a maximum of 64 characters long and values can be a maxium of 512 characters long.
	 */
	@Nullable
	Map<String, Object> metadata();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableAssistantCreateRequest.Builder {
	}

}
