package ch.rasc.openai4j.image;

import java.util.Optional;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableImageObject.class)
public interface ImageObject {
	/**
	 * The base64-encoded JSON of the generated image, if response_format is b64_json.
	 */
	@JsonProperty("b64_json")
	Optional<String> b64Json();

	/**
	 * The URL of the generated image, if response_format is url (default).
	 */
	Optional<String> url();

	/**
	 * The prompt that was used to generate the image, if there was any revision to the
	 * prompt.
	 */
	@JsonProperty("revised_prompt")
	Optional<String> revisedPrompt();

}
