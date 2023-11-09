package ch.rasc.openai4j.embedding;

import java.util.List;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableEmbeddingResponse.class)
@Value.Enclosing
public interface EmbeddingResponse {

	String object();

	List<EmbeddingObject> data();

	String model();

	@Value.Immutable(builder = false)
	@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
	@JsonDeserialize(as = ImmutableEmbeddingResponse.Usage.class)
	interface Usage {
		@JsonProperty("prompt_tokens")
		int promptTokens();

		@JsonProperty("total_tokens")
		int totalTokens();
	}

	Usage usage();
}
