package ch.rasc.openai4j.audio;

import java.io.File;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface Audio {

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
	default AudioTranscriptionResponse audioTranscriptions(
			AudioTranscriptionRequest request) {

		return this.audioTranscriptions(request.file().toFile(),
				request.model().toValue(), request.language().orElse(null),
				request.prompt().orElse(null),
				request.responseFormat().map(r -> r.toValue()).orElse(null),
				request.temperature().orElse(null));
	}

	/**
	 * Transcribes audio into the input language.
	 * 
	 * @return The transcribed text.
	 */
	@RequestLine("POST /audio/transcriptions")
	@Headers("Content-Type: multipart/form-data")
	AudioTranscriptionResponse audioTranscriptions(@Param("file") File file,
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
	default AudioTranslationResponse audioTranslations(AudioTranslationRequest request) {
		return this.audioTranslations(request.file().toFile(), request.model().toValue(),
				request.prompt().orElse(null),
				request.responseFormat().map(r -> r.toValue()).orElse(null),
				request.temperature().orElse(null));
	}

	/**
	 * Translates audio into English.
	 * 
	 * @return The translated text.
	 */
	@RequestLine("POST /audio/translations")
	@Headers("Content-Type: multipart/form-data")
	AudioTranslationResponse audioTranslations(@Param("file") File file,
			@Param("model") String model, @Param("prompt") String prompt,
			@Param("response_format") String responseFormat,
			@Param("temperature") Double temperature);

}
