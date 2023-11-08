package ch.rasc.openai4j.image;

import java.util.Optional;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonSerialize(as = ImmutableImageGenerationRequest.class)
@JsonInclude(Include.NON_ABSENT)
public interface ImageGenerationRequest {

	enum Model {
		DALL_E_2("dall-e-2"), DALL_E_3("dall-e-3");

		private final String value;

		Model(String value) {
			this.value = value;
		}

		@JsonValue
		public String toValue() {
			return this.value;
		}
	}

	enum ResponseFormat {
		URL("url"), B64_JSON("b64_json");

		private final String value;

		ResponseFormat(String value) {
			this.value = value;
		}

		@JsonValue
		public String toValue() {
			return this.value;
		}
	}

	enum Quality {
		STANDARD("standard"), HD("hd");

		private final String value;

		Quality(String value) {
			this.value = value;
		}

		@JsonValue
		public String toValue() {
			return this.value;
		}
	}

	enum Size {
		S_256("256x256"), S_512("512x512"), S_1024("1024x1024"), S_1792("1792x1024"),
		S_1024_1792("1024x1792");

		private final String value;

		Size(String value) {
			this.value = value;
		}

		@JsonValue
		public String toValue() {
			return this.value;
		}
	}

	enum Style {
		VIVID("vivid"), NATURAL("natural");

		private final String value;

		Style(String value) {
			this.value = value;
		}

		@JsonValue
		public String toValue() {
			return this.value;
		}
	}

	/**
	 * A text description of the desired image(s). The maximum length is 1000 characters
	 * for dall-e-2 and 4000 characters for dall-e-3.
	 */
	String prompt();

	/**
	 * The model to use for image generation. Defaults to dall-e-2
	 */
	Optional<Model> model();

	/**
	 * The number of images to generate. Must be between 1 and 10. For dall-e-3, only n=1
	 * is supported. Defaults to 1
	 */
	Optional<Integer> n();

	/**
	 * The quality of the image that will be generated. hd creates images with finer
	 * details and greater consistency across the image. This param is only supported for
	 * dall-e-3. Defaults to standard
	 */
	Optional<Quality> quality();

	/**
	 * The format in which the generated images are returned. Must be one of url or
	 * b64_json. Defaults to url
	 */
	@JsonProperty("response_format")
	Optional<ResponseFormat> responseFormat();

	/**
	 * The size of the generated images. Must be one of 256x256, 512x512, or 1024x1024 for
	 * dall-e-2. Must be one of 1024x1024, 1792x1024, or 1024x1792 for dall-e-3 models.
	 * Defaults to 1024x1024
	 */
	Optional<Size> size();

	/**
	 * The style of the generated images. Must be one of vivid or natural. Vivid causes
	 * the model to lean towards generating hyper-real and dramatic images. Natural causes
	 * the model to produce more natural, less hyper-real looking images. This param is
	 * only supported for dall-e-3. Defaults to vivid
	 */
	Optional<Style> style();

	/**
	 * A unique identifier representing your end-user, which can help OpenAI to monitor
	 * and detect abuse.
	 */
	Optional<String> user();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableImageGenerationRequest.Builder {
	}

}
