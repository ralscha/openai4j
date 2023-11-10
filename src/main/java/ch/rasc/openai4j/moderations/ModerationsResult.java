package ch.rasc.openai4j.moderations;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableModerationsResult.class)
public interface ModerationsResult {

	/**
	 * Whether the content violates
	 * <a href="https://platform.openai.com/policies/usage-policies">OpenAI's usage
	 * policies</a>.
	 */
	boolean flagged();

	/**
	 * A list of the categories, and whether they are flagged or not.
	 */
	ModerationsCategories categories();

	/**
	 * A list of the categories along with their scores as predicted by the model.
	 */
	@JsonProperty("category_scores")
	ModerationsCategoryScores categoryScores();

}