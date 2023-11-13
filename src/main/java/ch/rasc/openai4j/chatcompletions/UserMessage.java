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
	private final String name;

	private UserMessage(Object content, String name) {
		this.content = content;
		this.name = name;
	}

	/**
	 * Create a new user message with the given content.
	 * 
	 * @param content The contents of the user message.
	 */
	public static UserMessage of(String content) {
		return new UserMessage(content, null);
	}

	/**
	 * Create a new user message with an array of content parts with a defined type, each
	 * can be of typetext or image_url when passing in images
	 * 
	 * @param content A list of content parts.
	 */
	public static UserMessage of(List<Content> content) {
		return new UserMessage(List.copyOf(content), null);
	}

	/**
	 * Create a new user message with the given content.
	 * 
	 * @param content The contents of the user message.
	 * @param name An optional name for the participant. Provides the model information to
	 * differentiate between participants of the same role.
	 */
	public static UserMessage of(String content, String name) {
		return new UserMessage(content, name);
	}

	/**
	 * Create a new user message with an array of content parts with a defined type, each
	 * can be of typetext or image_url when passing in images
	 * 
	 * @param content A list of content parts.
	 * @param name An optional name for the participant. Provides the model information to
	 * differentiate between participants of the same role.
	 */
	public static UserMessage of(List<Content> content, String name) {
		return new UserMessage(List.copyOf(content), name);
	}

	public interface Content {
	}

	public record ImageContent(String type, @JsonProperty("image_url") ImageUrl imageUrl)
			implements Content {
		record ImageUrl(String url, String detail) {
			/**
			 * Either a URL of the image or the base64 encoded image data.
			 */
			public String url() {
				return this.url;
			}

			/**
			 * Specifies the detail level of the image.
			 */
			public String detail() {
				return this.detail;
			}
		}

		/**
		 * Creates a new ImageContent object
		 *
		 * @param url Either a URL of the image or the base64 encoded image data.
		 */
		public static ImageContent of(String url) {
			if (url == null) {
				throw new IllegalArgumentException("url cannot be null");
			}
			return of(url, null);
		}

		/**
		 * Creates a new ImageContent object
		 *
		 * @param url Either a URL of the image or the base64 encoded image data.
		 * @param detail Specifies the detail level of the image.
		 */
		public static ImageContent of(String url, String detail) {
			if (url == null) {
				throw new IllegalArgumentException("url cannot be null");
			}
			return new ImageContent("image_url", new ImageUrl(url, detail));
		}

		public ImageUrl imageUrl() {
			return this.imageUrl;
		}

		/**
		 * The type of the content part.
		 */
		public String type() {
			return this.type;
		}
	}

	public record TextContent(String type, String text) implements Content {
		/**
		 * @param text The text content.
		 */
		public static TextContent of(String text) {
			if (text == null) {
				throw new IllegalArgumentException("text cannot be null");
			}
			return new TextContent("text", text);
		}

		/**
		 * The text content.
		 */
		public String text() {
			return this.text;
		}

		/**
		 * The type of the content part.
		 */
		public String type() {
			return this.type;
		}
	}

	/**
	 * The contents of the user message. Either a String or an array of content parts
	 */
	@JsonInclude
	@JsonProperty
	public Object content() {
		return this.content;
	}

	/**
	 * An optional name for the participant. Provides the model information to
	 * differentiate between participants of the same role.
	 */
	@JsonProperty
	public String name() {
		return this.name;
	}

	/**
	 * The role of the messages author, in this case <code>user</code>.
	 */
	@Override
	String role() {
		return "user";
	}

}
