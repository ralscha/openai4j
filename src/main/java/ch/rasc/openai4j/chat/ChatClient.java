package ch.rasc.openai4j.chat;

import feign.Headers;
import feign.RequestLine;

public interface ChatClient {

	/**
	 * Creates a completion for the provided prompt and parameters.
	 *
	 * @return Returns a completion object
	 */
	@RequestLine("POST /chat/completions")
	@Headers("Content-Type: application/json")
	ChatCompletionResponse completion(ChatCompletionRequest request);
}
