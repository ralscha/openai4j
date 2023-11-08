package ch.rasc.openai4j.embedding;

import feign.Headers;
import feign.RequestLine;

public interface EmbeddingClient {

	/**
	 * Creates an embedding vector representing the input text.
	 *
	 * @return A list of embedding objects.
	 */
	@RequestLine("POST /embeddings")
	@Headers("Content-Type: application/json")
	EmbeddingResponse embedding(EmbeddingRequest request);

}
