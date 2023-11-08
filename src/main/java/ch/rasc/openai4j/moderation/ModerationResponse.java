package ch.rasc.openai4j.moderation;

import java.util.List;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonDeserialize(as = ImmutableModerationResponse.class)
public interface ModerationResponse {

	/**
	 * The unique identifier for the moderation request.
	 */
	@Value.Parameter
	String id();

	/**
	 * The model used to generate the moderation results.
	 */
	@Value.Parameter
	String model();

	/**
	 * A list of moderation objects.
	 */
	@Value.Parameter
	List<ModerationResult> results();

}
