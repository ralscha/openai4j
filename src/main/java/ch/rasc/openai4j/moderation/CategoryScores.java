package ch.rasc.openai4j.moderation;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonDeserialize(as = ImmutableCategoryScores.class)
public interface CategoryScores {

	@Value.Parameter
	Double hate();

	@Value.Parameter
	@JsonProperty("hate/threatening")
	Double hateThreatening();

	@Value.Parameter
	Double harassment();

	@Value.Parameter
	@JsonProperty("harassment/threatening")
	Double harassmentThreatening();

	@Value.Parameter
	@JsonProperty("self-harm")
	Double selfHarm();

	@Value.Parameter
	@JsonProperty("self-harm/intent")
	Double selfHarmIntent();

	@Value.Parameter
	@JsonProperty("self-harm/instructions")
	Double selfHarmInstructions();

	@Value.Parameter
	Double sexual();

	@Value.Parameter
	@JsonProperty("sexual/minors")
	Double sexualMinors();

	@Value.Parameter
	Double violence();

	@Value.Parameter
	@JsonProperty("violence/graphic")
	Double violenceGraphic();

}
