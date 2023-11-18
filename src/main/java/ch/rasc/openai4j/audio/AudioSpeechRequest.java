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

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class AudioSpeechRequest {

	private final SpeechModel model;
	private final String input;
	private final Voice voice;
	@JsonProperty("response_format")
	private final AudioResponseFormat responseFormat;
	private final Double speed;

	private AudioSpeechRequest(Builder builder) {
		if (builder.model == null) {
			throw new IllegalArgumentException("model is required");
		}
		if (builder.input == null || builder.input.isBlank()) {
			throw new IllegalArgumentException("input is required");
		}
		if (builder.voice == null) {
			throw new IllegalArgumentException("voice is required");
		}
		this.model = builder.model;
		this.input = builder.input;
		this.voice = builder.voice;
		this.responseFormat = builder.responseFormat;
		this.speed = builder.speed;
	}

	public enum AudioResponseFormat {
		MP3("mp3"), OPUS("opus"), AAC("aac"), FLAC("flac");

		private final String value;

		AudioResponseFormat(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	public enum Voice {
		ALLOY("alloy"), ECHO("echo"), FABLE("fable"), ONYX("onyx"), NOVA("nova"),
		SHIMMER("shimmer");

		private final String value;

		Voice(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	public enum SpeechModel {
		TTS_1("tts-1"), TTS_1_HD("tts-1-hd");

		private final String value;

		SpeechModel(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private SpeechModel model;
		private String input;
		private Voice voice;
		private AudioResponseFormat responseFormat;
		private Double speed;

		private Builder() {
		}

		/**
		 * One of the available TTS models: tts-1 or tts-1-hd
		 */
		public Builder model(SpeechModel val) {
			this.model = val;
			return this;
		}

		/**
		 * The text to generate audio for. The maximum length is 4096 characters.
		 */
		public Builder input(String val) {
			this.input = val;
			return this;
		}

		/**
		 * The voice to use when generating the audio. Supported voices are alloy, echo,
		 * fable, onyx, nova, and shimmer.
		 */
		public Builder voice(Voice val) {
			this.voice = val;
			return this;
		}

		/**
		 * The format to audio in. Defaults to mp3
		 */
		public Builder responseFormat(AudioResponseFormat val) {
			this.responseFormat = val;
			return this;
		}

		/**
		 * The speed of the generated audio. Select a value from 0.25 to 4.0. 1.0 is the
		 * default.
		 */
		public Builder speed(Double val) {
			this.speed = val;
			return this;
		}

		public AudioSpeechRequest build() {
			return new AudioSpeechRequest(this);
		}
	}

}
