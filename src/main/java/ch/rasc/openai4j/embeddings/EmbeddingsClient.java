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
	EmbeddingCreateResponse create(EmbeddingCreateRequest request);

	/**
	 * Creates an embedding vector representing the input text.
	 *
	 * @return A list of embedding objects.
	 */
	default EmbeddingCreateResponse create(
			Function<EmbeddingCreateRequest.Builder, EmbeddingCreateRequest.Builder> fn) {
		return this.create(fn.apply(EmbeddingCreateRequest.builder()).build());
	}

}
