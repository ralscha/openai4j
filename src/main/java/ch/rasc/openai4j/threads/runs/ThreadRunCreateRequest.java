package ch.rasc.openai4j.threads.runs;

import java.util.Map;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.rasc.openai4j.Nullable;
import ch.rasc.openai4j.assistants.Tool;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonSerialize(as = ImmutableThreadRunCreateRequest.class)
@JsonInclude(Include.NON_EMPTY)
public interface ThreadRunCreateRequest {

	/*
	 * The ID of the assistant to use to execute this run.
	 */
	@JsonProperty("assistant_id")
	String assistantId();

	/*
	 * The ID of the Model to be used to execute this run. If a value is provided here, it
	 * will override the model associated with the assistant. If not, the model associated
	 * with the assistant will be used.
	 */
	@Nullable
	String model();

	/*
	 * Override the default system message of the assistant. This is useful for modifying
	 * the behavior on a per-run basis.
	 */
	@Nullable
	String instructions();

	/*
	 * Override the tools the assistant can use for this run. This is useful for modifying
	 * the behavior on a per-run basis.
	 */
	@Nullable
	Tool[] tools();

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

	final class Builder extends ImmutableThreadRunCreateRequest.Builder {
	}
}
