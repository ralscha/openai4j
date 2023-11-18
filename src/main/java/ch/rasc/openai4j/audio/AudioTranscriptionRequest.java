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

public class AudioTranscriptionRequest {

	private final Path file;
	private final AudioRecognitionModel model;
	private final String language;
	private final String prompt;
	private final AudioRecognitionResponseFormat responseFormat;
	private final Double temperature;

	private AudioTranscriptionRequest(Builder builder) {
		if (builder.file == null) {
			throw new IllegalArgumentException("file is required");
		}
		if (builder.model == null) {
			throw new IllegalArgumentException("model is required");
		}
		this.file = builder.file;
		this.model = builder.model;
		this.language = builder.language;
		this.prompt = builder.prompt;
		this.responseFormat = builder.responseFormat;
		this.temperature = builder.temperature;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private Path file;
		private AudioRecognitionModel model;
		private String language;
		private String prompt;
		private AudioRecognitionResponseFormat responseFormat;
		private Double temperature;

		private Builder() {
		}

		/**
		 * The audio file object to transcribe, in one of these formats: flac, mp3, mp4,
		 * mpeg, mpga, m4a, ogg, wav, or webm.
		 */
		public Builder file(Path val) {
			this.file = val;
			return this;
		}

		/**
		 * ID of the model to use. Only whisper-1 is currently available.
		 */
		public Builder model(AudioRecognitionModel val) {
			this.model = val;
			return this;
		}

		/**
		 * The language of the input audio. Supplying the input language in ISO-639-1
		 * format will improve accuracy and latency.
		 */
		public Builder language(String val) {
			this.language = val;
			return this;
		}

		/**
		 * An optional text to guide the model's style or continue a previous audio
		 * segment. The prompt should match the audio language.
		 */
		public Builder prompt(String val) {
			this.prompt = val;
			return this;
		}

		/**
		 * The format of the transcript output. Defaults to json.
		 */
		public Builder responseFormat(AudioRecognitionResponseFormat val) {
			this.responseFormat = val;
			return this;
		}

		/**
		 * The sampling temperature, between 0 and 1. Higher values like 0.8 will make the
		 * output more random, while lower values like 0.2 will make it more focused and
		 * deterministic. If set to 0, the model will use log probability to automatically
		 * increase the temperature until certain thresholds are hit.
		 * <p>
		 * Defaults to 0.
		 */
		public Builder temperature(Double val) {
			this.temperature = val;
			return this;
		}

		public AudioTranscriptionRequest build() {
			return new AudioTranscriptionRequest(this);
		}
	}

	public Path file() {
		return this.file;
	}

	public AudioRecognitionModel model() {
		return this.model;
	}

	public String language() {
		return this.language;
	}

	public String prompt() {
		return this.prompt;
	}

	public AudioRecognitionResponseFormat responseFormat() {
		return this.responseFormat;
	}

	public Double temperature() {
		return this.temperature;
	}
}
