package ch.rasc.openai4j.moderations;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableModerationsCategoryScores.class)
public interface ModerationsCategoryScores {

	Double hate();

	@JsonProperty("hate/threatening")
	Double hateThreatening();

	Double harassment();

	@JsonProperty("harassment/threatening")
	Double harassmentThreatening();

	@JsonProperty("self-harm")
	Double selfHarm();

	@JsonProperty("self-harm/intent")
	Double selfHarmIntent();

	@JsonProperty("self-harm/instructions")
	Double selfHarmInstructions();

	Double sexual();

	@JsonProperty("sexual/minors")
	Double sexualMinors();

	Double violence();

	@JsonProperty("violence/graphic")
	Double violenceGraphic();

}
