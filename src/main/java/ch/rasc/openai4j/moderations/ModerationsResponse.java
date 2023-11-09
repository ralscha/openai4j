package ch.rasc.openai4j.moderations;

import java.util.List;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableModerationsResponse.class)
public interface ModerationsResponse {

	/**
	 * The unique identifier for the moderation request.
	 */
	String id();

	/**
	 * The model used to generate the moderation results.
	 */
	String model();

	/**
	 * A list of moderation objects.
	 */
	List<ModerationsResult> results();

}
