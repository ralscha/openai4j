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
package ch.rasc.openai4j.chatcompletions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMessage extends ChatCompletionMessage {
	private final Object content;

	UserMessage(Object content) {
		this.content = content;
	}

	/**
	 * Create a new user message with the given content.
	 */
	public static UserMessage of(String content) {
		return new UserMessage(content);
	}

	/**
	 * Create a new user message with the given content.
	 */
	public static UserMessage of(List<Content> content) {
		return new UserMessage(List.copyOf(content));
	}

	public interface Content {
	}

	public record ImageContent(String type, @JsonProperty("image_url") ImageUrl imageUrl)
			implements Content {
		record ImageUrl(String url, String detail) {
		}

		public static ImageContent of(String url) {
			return of(url, null);
		}

		public static ImageContent of(String url, String detail) {
			return new ImageContent("image_url", new ImageUrl(url, detail));
		}
	}

	public record TextContent(String type, String text) implements Content {
		public static TextContent of(String text) {
			return new TextContent("text", text);
		}
	}

	/**
	 * The contents of the user message.
	 */
	@JsonInclude
	@JsonProperty
	public Object content() {
		return this.content;
	}

	@Override
	String role() {
		return "user";
	}

}
