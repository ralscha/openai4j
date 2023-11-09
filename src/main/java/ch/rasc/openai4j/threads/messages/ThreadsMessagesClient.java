package ch.rasc.openai4j.threads.messages;

import java.util.function.Function;

import ch.rasc.openai4j.Beta;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Beta
public interface ThreadsMessagesClient {

	/**
	 * Returns a list of messages for a given thread.
	 *
	 * @return A list of message objects.
	 */
	@RequestLine("POST /threads/{thread_id}/messages")
	ThreadMessagesResponse list(@Param("thread_id") String threadId);

	/**
	 * Create a message.
	 *
	 * @return A message object.
	 */
	@RequestLine("POST /threads/{thread_id}/messages")
	@Headers("Content-Type: application/json")
	ThreadMessageObject create(@Param("thread_id") String threadId,
			ThreadMessageCreateRequest request);

	/**
	 * Create a message.
	 *
	 * @return A message object.
	 */
	default ThreadMessageObject create(@Param("thread_id") String threadId,
			Function<ThreadMessageCreateRequest.Builder, ThreadMessageCreateRequest.Builder> fn) {
		return this.create(threadId,
				fn.apply(ThreadMessageCreateRequest.builder()).build());
	}

	/**
	 * Retrieve a message.
	 *
	 * @return The message object matching the specified ID.
	 */
	@RequestLine("GET /threads/{thread_id}/messages/{message_id}")
	ThreadMessageObject retrieve(@Param("thread_id") String threadId,
			@Param("message_id") String messageId);

	/**
	 * Modifies a message.
	 *
	 * @return The modified message object.
	 */
	@RequestLine("POST /threads/{thread_id}/messages/{message_id}")
	@Headers("Content-Type: application/json")
	ThreadMessageObject update(@Param("thread_id") String threadId,
			@Param("message_id") String messageId, ThreadMessageUpdateRequest request);

}
