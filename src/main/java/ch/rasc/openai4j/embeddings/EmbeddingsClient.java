package ch.rasc.openai4j.embeddings;

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
	EmbeddingResponse embedding(EmbeddingRequest request);

}
