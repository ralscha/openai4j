package ch.rasc.openai4j.audio;

import java.io.File;

import ch.rasc.openai4j.audio.AudioTranscriptionRequest.ResponseFormat;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface AudioClient {

	/**
	 * Generates audio from the input text.
	 *
	 * @return The audio file content.
	 */
	@RequestLine("POST /audio/speech")
	@Headers("Content-Type: application/json")
	Response audioSpeech(AudioSpeechRequest request);

	/**
	 * Transcribes audio into the input language.
	 *
	 * @return The transcribed text.
	 */
	default AudioTranscriptionResponse audioTranscription(
			AudioTranscriptionRequest request) {

		return this.audioTranscription(request.file().toFile(), request.model().toValue(),
				request.language().orElse(null), request.prompt().orElse(null),
				request.responseFormat().map(ResponseFormat::toValue).orElse(null),
				request.temperature().orElse(null));
	}

	/**
	 * Transcribes audio into the input language.
	 *
	 * @return The transcribed text.
	 */
	@RequestLine("POST /audio/transcriptions")
	@Headers("Content-Type: multipart/form-data")
	AudioTranscriptionResponse audioTranscription(@Param("file") File file,
			@Param("model") String model, @Param("language") String language,
			@Param("prompt") String prompt,
			@Param("response_format") String responseFormat,
			@Param("temperature") Double temperature);

	/**
	 * Translates audio into English.
	 *
	 * @return The translated text.
	 */
	@RequestLine("POST /audio/translations")
	@Headers("Content-Type: multipart/form-data")
	default AudioTranslationResponse audioTranslation(AudioTranslationRequest request) {
		return this.audioTranslation(request.file().toFile(), request.model().toValue(),
				request.prompt().orElse(null),
				request.responseFormat().map(
						ch.rasc.openai4j.audio.AudioTranslationRequest.ResponseFormat::toValue)
						.orElse(null),
				request.temperature().orElse(null));
	}

	/**
	 * Translates audio into English.
	 *
	 * @return The translated text.
	 */
	@RequestLine("POST /audio/translations")
	@Headers("Content-Type: multipart/form-data")
	AudioTranslationResponse audioTranslation(@Param("file") File file,
			@Param("model") String model, @Param("prompt") String prompt,
			@Param("response_format") String responseFormat,
			@Param("temperature") Double temperature);

}
