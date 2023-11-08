package ch.rasc.openai4j.audio;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable
@JsonDeserialize(as = ImmutableAudioTranslationResponse.class)
public interface AudioTranslationResponse {

	/**
	 * The translated text.
	 */
	String text();
}
