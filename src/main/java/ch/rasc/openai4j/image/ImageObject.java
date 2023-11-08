package ch.rasc.openai4j.image;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableImageObject.class)
public interface ImageObject {
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
