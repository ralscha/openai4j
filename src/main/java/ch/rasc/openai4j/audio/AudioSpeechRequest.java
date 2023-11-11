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

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonSerialize(as = ImmutableAudioSpeechRequest.class)
@JsonInclude(Include.NON_EMPTY)
public interface AudioSpeechRequest {

	static Builder builder() {
		return new Builder();
	}

	/**
	 * One of the available <a href="https://platform.openai.com/docs/models/tts">TTS
	 * models</a>
	 */
	Model model();

	/**
	 * The text to generate audio for. The maximum length is 4096 characters.
	 */
	String input();

	/**
	 * The voice to use when generating the audio.
	 */
	Voice voice();

	/**
	 * The format to audio in. Defaults to mp3
	 */
	@JsonProperty("response_format")
	@Nullable
	ResponseFormat responseFormat();

	/**
	 * The speed of the generated audio. Defaults to 1.0
	 */
	@Nullable
	Double speed();

	enum ResponseFormat {
		MP3("mp3"), OPUS("opus"), AAC("aac"), FLAC("flac");

		private final String value;

		ResponseFormat(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	enum Voice {
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

	enum Model {
		TTS_1("tts-1"), TTS_1_HD("tts-1-hd");

		private final String value;

		Model(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	final class Builder extends ImmutableAudioSpeechRequest.Builder {
	}
}
