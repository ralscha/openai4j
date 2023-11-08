package ch.rasc.openai4j.moderation;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonDeserialize(as = ImmutableCategories.class)
public interface Categories {

	/**
	 * Content that expresses, incites, or promotes hate based on race, gender, ethnicity,
	 * religion, nationality, sexual orientation, disability status, or caste. Hateful
	 * content aimed at non-protected groups (e.g., chess players) is harrassment.
	 */
	@Value.Parameter
	boolean hate();

	/**
	 * Hateful content that also includes violence or serious harm towards the targeted
	 * group based on race, gender, ethnicity, religion, nationality, sexual orientation,
	 * disability status, or caste.
	 */
	@Value.Parameter
	@JsonProperty("hate/threatening")
	boolean hateThreatening();

	/**
	 * Content that expresses, incites, or promotes harassing language towards any target.
	 */
	@Value.Parameter
	boolean harassment();

	/**
	 * Harassment content that also includes violence or serious harm towards any target.
	 */
	@Value.Parameter
	@JsonProperty("harassment/threatening")
	boolean harassmentThreatening();

	/**
	 * Content that promotes, encourages, or depicts acts of self-harm, such as suicide,
	 * cutting, and eating disorders
	 */
	@Value.Parameter
	@JsonProperty("self-harm")
	boolean selfHarm();

	/**
	 * Content where the speaker expresses that they are engaging or intend to engage in
	 * acts of self-harm, such as suicide, cutting, and eating disorders.
	 */
	@Value.Parameter
	@JsonProperty("self-harm/intent")
	boolean selfHarmIntent();

	/**
	 * Content that encourages performing acts of self-harm, such as suicide, cutting, and
	 * eating disorders, or that gives instructions or advice on how to commit such acts.
	 */
	@Value.Parameter
	@JsonProperty("self-harm/instructions")
	boolean selfHarmInstructions();

	/**
	 * Content meant to arouse sexual excitement, such as the description of sexual
	 * activity, or that promotes sexual services (excluding sex education and wellness).
	 */
	@Value.Parameter
	boolean sexual();

	/**
	 * Sexual content that includes an individual who is under 18 years old.
	 */
	@Value.Parameter
	@JsonProperty("sexual/minors")
	boolean sexualMinors();

	/**
	 * Content that depicts death, violence, or physical injury.
	 */
	@Value.Parameter
	boolean violence();

	/**
	 * Content that depicts death, violence, or physical injury in graphic detail.
	 */
	@Value.Parameter
	@JsonProperty("violence/graphic")
	boolean violenceGraphic();

}
