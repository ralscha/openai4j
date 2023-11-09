package ch.rasc.openai4j.embeddings;

import java.util.List;

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
@JsonSerialize(as = ImmutableEmbeddingRequest.class)
@JsonInclude(Include.NON_EMPTY)
public interface EmbeddingRequest {

	enum EncodingFormat {
		FLOAT("float"), BASE64("base64");

		private final String value;

		EncodingFormat(String value) {
			this.value = value;
		}

		@JsonValue
		public String toValue() {
			return this.value;
		}
	}

	/**
	 * Input text to embed, encoded as a string or array of tokens. To embed multiple
	 * inputs in a single request, pass an array of strings or array of token arrays. The
	 * input must not exceed the max input tokens for the model (8192 tokens for
	 * text-embedding-ada-002) and cannot be an empty string.
	 */
	List<String> input();

	/**
	 * ID of the model to use. You can use the List models API to see all of your
	 * available models, or see our Model overview for descriptions of them.
	 */
	String model();

	/**
	 * The format to return the embeddings in. Can be either float or base64. Defaults to
	 * float.
	 */
	@Nullable
	@JsonProperty("encoding_format")
	EncodingFormat encodingFormat();

	/**
	 * A unique identifier representing your end-user, which can help OpenAI to monitor
	 * and detect abuse.
	 */
	@Nullable
	String user();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableEmbeddingRequest.Builder {
	}
}
