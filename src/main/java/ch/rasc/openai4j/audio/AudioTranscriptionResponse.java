package ch.rasc.openai4j.audio;

import org.immutables.value.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable
@JsonDeserialize(as = ImmutableAudioTranscriptionResponse.class)
public interface AudioTranscriptionResponse {

	/**
	 * The transcribed text.
	 */
	String text();
}
