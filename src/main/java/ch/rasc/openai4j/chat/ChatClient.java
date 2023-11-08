package ch.rasc.openai4j.chat;

import feign.Headers;
import feign.RequestLine;

public interface ChatClient {

	/**
	 * Creates a completion for the provided prompt and parameters.
	 *
	 * @return Returns a completion object
	 */
	@RequestLine("POST /completions")
	@Headers("Content-Type: application/json")
	ChatCompletionObject completion(ChatCompletionRequest request);
}
