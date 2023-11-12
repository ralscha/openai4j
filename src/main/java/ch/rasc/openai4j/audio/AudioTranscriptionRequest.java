/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.rasc.openai4j.audio;

import java.nio.file.Path;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE, depluralize = true)
public interface AudioTranscriptionRequest {

	static Builder builder() {
		return new Builder();
	}

	/**
	 * The audio file object to transcribe, in one of these formats: flac, mp3, mp4, mpeg,
	 * mpga, m4a, ogg, wav, or webm.
	 */
	Path file();

	/**
	 * ID of the model to use. Only whisper-1 is currently available.
	 */
	@Value.Default
	default Model model() {
		return Model.WHISPER_1;
	}

	/**
	 * The language of the input audio. Supplying the input language in ISO-639-1 format
	 * will improve accuracy and latency.
	 */
	@Nullable
	String language();

	/**
	 * An optional text to guide the model's style or continue a previous audio segment.
	 * The prompt should match the audio language.
	 */
	@Nullable
	String prompt();

	/**
	 * The format of the transcript output. Defaults to json.
	 */
	@JsonProperty("response_format")
	@Nullable
	ResponseFormat responseFormat();

	/**
	 * The sampling temperature, between 0 and 1. Higher values like 0.8 will make the
	 * output more random, while lower values like 0.2 will make it more focused and
	 * deterministic. If set to 0, the model will use log probability to automatically
	 * increase the temperature until certain thresholds are hit.
	 * <p>
	 * Defaults to 0.
	 */
	@Nullable
	Double temperature();

	enum ResponseFormat {
		JSON("json"), TEXT("text"), SRT("srt"), VERBOSE_JSON("verbose_json"), VTT("vtt");

		private final String value;

		ResponseFormat(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	enum Model {
		WHISPER_1("whisper-1");

		private final String value;

		Model(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	final class Builder extends ImmutableAudioTranscriptionRequest.Builder {
	}
}
