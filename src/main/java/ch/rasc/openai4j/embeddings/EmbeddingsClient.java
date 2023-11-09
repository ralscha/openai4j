package ch.rasc.openai4j.embeddings;

import java.util.function.Function;

import feign.Headers;
import feign.RequestLine;

public interface EmbeddingsClient {

	/**
	 * Creates an embedding vector representing the input text.
	 *
	 * @return A list of embedding objects.
	 */
	@RequestLine("POST /embeddings")
	@Headers("Content-Type: application/json")
	EmbeddingsResponse create(EmbeddingsRequest request);

	/**
	 * Creates an embedding vector representing the input text.
	 *
	 * @return A list of embedding objects.
	 */
	default EmbeddingsResponse create(
			Function<EmbeddingsRequest.Builder, EmbeddingsRequest.Builder> fn) {
		return this.create(fn.apply(EmbeddingsRequest.builder()).build());
	}

}
