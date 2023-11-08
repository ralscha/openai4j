package ch.rasc.openai4j.audio;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonDeserialize(as = ImmutableAudioTranslationResponse.class)
public interface AudioTranslationResponse {

	/**
	 * The translated text.
	 */
	@Value.Parameter
	String text();
}
