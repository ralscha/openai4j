package ch.rasc.openai4j.embeddings;

import java.util.List;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableEmbeddingCreateResponse.class)
@Value.Enclosing
public interface EmbeddingCreateResponse {

	String object();

	List<Embedding> data();

	String model();

	@Value.Immutable(builder = false)
	@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
	@JsonDeserialize(as = ImmutableEmbeddingCreateResponse.Usage.class)
	interface Usage {
		@JsonProperty("prompt_tokens")
		int promptTokens();

		@JsonProperty("total_tokens")
		int totalTokens();
	}

	Usage usage();
}
