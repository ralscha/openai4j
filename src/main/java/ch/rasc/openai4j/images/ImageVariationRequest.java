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
package ch.rasc.openai4j.images;

import java.nio.file.Path;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE, depluralize = true)
public interface ImageVariationRequest {

	static Builder builder() {
		return new Builder();
	}

	/**
	 * The image to use as the basis for the variation(s). Must be a valid PNG file, less
	 * than 4MB, and square.
	 */
	Path image();

	/**
	 * The model to use for image generation. Only dall-e-2 is supported at this time.
	 * Defaults to dall-e-2.
	 */
	@Nullable
	ImageModel model();

	/**
	 * The number of images to generate. Must be between 1 and 10. For dall-e-3, only n=1
	 * is supported. Defaults to 1
	 */
	@Nullable
	Integer n();

	/**
	 * The format in which the generated images are returned. Must be one of url or
	 * b64_json. Defaults to url.
	 */
	@Nullable
	ImageResponseFormat responseFormat();

	/**
	 * The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024.
	 * Defaults to 1024x1024.
	 */
	@Nullable
	ImageSize size();

	/**
	 * A unique identifier representing your end-user, which can help OpenAI to monitor
	 * and detect abuse.
	 */
	@Nullable
	String user();

	final class Builder extends ImmutableImageVariationRequest.Builder {
	}

}
