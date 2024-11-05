package ch.rasc.openai4j.chatcompletions;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@SuppressWarnings({ "unused", "hiding" })
public class Prediction {
	private final String type;

	private final Object content;

	public record ContentPart(String type, String text) {
	}

	private Prediction(Builder builder) {
		this.type = "content";
		this.content = builder.content;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private Object content;

		private Builder() {
		}

		/**
		 * The content that should be matched when generating a model response. If
		 * generated tokens would match this content, the entire model response can be
		 * returned much more quickly.
		 * <p>
		 * The content used for a Predicted Output. This is often the text of a file you
		 * are regenerating with minor changes.
		 */
		public Builder content(String content) {
			this.content = content;
			return this;
		}

		/**
		 * The content that should be matched when generating a model response. If
		 * generated tokens would match this content, the entire model response can be
		 * returned much more quickly.
		 * <p>
		 * An array of content parts with a defined type. Supported options differ based
		 * on the model being used to generate the response. Can contain text inputs.
		 */
		public Builder contentPart(List<ContentPart> contentParts) {
			if (contentParts != null) {
				this.content = new ArrayList<>(contentParts);
			}
			return this;
		}

		/**
		 * The content that should be matched when generating a model response. If
		 * generated tokens would match this content, the entire model response can be
		 * returned much more quickly.
		 * <p>
		 * An array of content parts with a defined type. Supported options differ based
		 * on the model being used to generate the response. Can contain text inputs.
		 */
		public Builder addContentParts(ContentPart... contentParts) {
			if (this.content == null) {
				this.content = new ArrayList<>();
			}
			if (this.content instanceof String) {
				throw new IllegalStateException(
						"Cannot add content parts to a string content");
			}
			for (ContentPart contentPart : contentParts) {
				((List<ContentPart>) this.content).add(contentPart);
			}
			return this;
		}

		public Prediction build() {
			return new Prediction(this);
		}
	}

}
