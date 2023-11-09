package ch.rasc.openai4j.assistants.files;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE)
@JsonSerialize(as = ImmutableAssistantFileCreateRequest.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public interface AssistantFileCreateRequest {

	/**
	 * A File ID (with purpose="assistants") that the assistant should use. Useful for
	 * tools like retrieval and code_interpreter that can access files.
	 */
	@JsonProperty("file_id")
	String fileId();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableAssistantFileCreateRequest.Builder {
	}
}
