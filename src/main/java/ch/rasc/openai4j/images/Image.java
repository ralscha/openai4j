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

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableImage.class)
public interface Image {
	/**
	 * The base64-encoded JSON of the generated image, if response_format is b64_json.
	 */
	@JsonProperty("b64_json")
	@Nullable
	String b64Json();

	/**
	 * The URL of the generated image, if response_format is url (default).
	 */
	@Nullable
	String url();

	/**
	 * The prompt that was used to generate the image, if there was any revision to the
	 * prompt.
	 */
	@JsonProperty("revised_prompt")
	@Nullable
	String revisedPrompt();

}
