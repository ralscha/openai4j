package ch.rasc.openai4j.audio;

import java.io.File;
import java.util.function.Function;

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
	Response create(AudioSpeechRequest request);

	/**
	 * Generates audio from the input text.
	 *
	 * @return The audio file content.
	 */
	default Response create(
			Function<AudioSpeechRequest.Builder, AudioSpeechRequest.Builder> fn) {
		return this.create(fn.apply(AudioSpeechRequest.builder()).build());
	}

	/**
	 * Transcribes audio into the input language.
	 *
	 * @return The transcribed text.
	 */
	default AudioTranscriptionResponse transcriptionsCreate(
			Function<AudioTranscriptionRequest.Builder, AudioTranscriptionRequest.Builder> fn) {
		return this.transcriptionsCreate(
				fn.apply(AudioTranscriptionRequest.builder()).build());
	}

	/**
	 * Transcribes audio into the input language.
	 *
	 * @return The transcribed text.
	 */
	default AudioTranscriptionResponse transcriptionsCreate(
			AudioTranscriptionRequest request) {

		return this.transcriptionsCreate(request.file().toFile(), request.model().value(),
				request.language(), request.prompt(),
				request.responseFormat() != null ? request.responseFormat().value()
						: null,
				request.temperature());
	}

	/**
	 * Transcribes audio into the input language.
	 *
	 * @return The transcribed text.
	 */
	@RequestLine("POST /audio/transcriptions")
	@Headers("Content-Type: multipart/form-data")
	AudioTranscriptionResponse transcriptionsCreate(@Param("file") File file,
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
	default AudioTranslationResponse translationsCreate(
			Function<AudioTranslationRequest.Builder, AudioTranslationRequest.Builder> fn) {
		return this
				.translationsCreate(fn.apply(AudioTranslationRequest.builder()).build());
	}

	/**
	 * Translates audio into English.
	 *
	 * @return The translated text.
	 */
	@RequestLine("POST /audio/translations")
	@Headers("Content-Type: multipart/form-data")
	default AudioTranslationResponse translationsCreate(AudioTranslationRequest request) {
		return this.translationsCreate(request.file().toFile(), request.model().value(),
				request.prompt(),
				request.responseFormat() != null ? request.responseFormat().value()
						: null,
				request.temperature());
	}

	/**
	 * Translates audio into English.
	 *
	 * @return The translated text.
	 */
	@RequestLine("POST /audio/translations")
	@Headers("Content-Type: multipart/form-data")
	AudioTranslationResponse translationsCreate(@Param("file") File file,
			@Param("model") String model, @Param("prompt") String prompt,
			@Param("response_format") String responseFormat,
			@Param("temperature") Double temperature);

}
