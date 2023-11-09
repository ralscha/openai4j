package ch.rasc.openai4j.finetuning;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable(builder = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE,
		allParameters = true)
@JsonDeserialize(as = ImmutableFineTuningJobEvent.class)
public interface FineTuningJobEvent {

	String id();

	@JsonProperty("created_at")
	int createdAt();

	String level();

	String message();

	String object();

	@Nullable
	Object data();

	String type();

}
