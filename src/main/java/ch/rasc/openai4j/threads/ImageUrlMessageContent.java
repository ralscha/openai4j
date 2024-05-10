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
package ch.rasc.openai4j.threads;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ch.rasc.openai4j.common.ImageDetail;
import ch.rasc.openai4j.common.ImageUrl;

public class ImageUrlMessageContent extends MessageContent {

	private final ImageUrl imageUrl;

	private ImageUrlMessageContent(ImageUrl imageUrl) {
		super("image_url");
		this.imageUrl = imageUrl;
	}

	public static ImageUrlMessageContent of(String url) {
		return new ImageUrlMessageContent(new ImageUrl(url, null));
	}

	@JsonCreator
	public static ImageUrlMessageContent of(String url, ImageDetail detail) {
		return new ImageUrlMessageContent(new ImageUrl(url, detail));
	}

	@JsonProperty("image_url")
	public ImageUrl imageUrl() {
		return this.imageUrl;
	}
}