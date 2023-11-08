package ch.rasc.openai4j.audio;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonDeserialize(as = ImmutableAudioTranscriptionResponse.class)
public interface AudioTranscriptionResponse {

	/**
	 * The transcribed text.
	 */
	@Value.Parameter
	String text();
}
