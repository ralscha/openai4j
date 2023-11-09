package ch.rasc.openai4j.chatcompletions;

import java.util.function.Function;

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
	ChatCompletionsResponse create(ChatCompletionsCreateRequest request);

	/**
	 * Creates a completion for the provided prompt and parameters.
	 *
	 * @return Returns a completion object
	 */
	default ChatCompletionsResponse create(
			Function<ChatCompletionsCreateRequest.Builder, ChatCompletionsCreateRequest.Builder> fn) {
		return this.create(fn.apply(ChatCompletionsCreateRequest.builder()).build());
	}
}
