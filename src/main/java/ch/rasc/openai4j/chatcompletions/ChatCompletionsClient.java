package ch.rasc.openai4j.chatcompletions;

import feign.Headers;
import feign.RequestLine;

public interface ChatCompletionsClient {

	/**
	 * Creates a completion for the provided prompt and parameters.
	 *
	 * @return Returns a completion object
	 */
	@RequestLine("POST /chat/completions")
	@Headers("Content-Type: application/json")
	ChatCompletionResponse create(ChatCompletionCreateRequest request);
}
