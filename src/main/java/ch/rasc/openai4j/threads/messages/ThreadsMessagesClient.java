package ch.rasc.openai4j.threads.messages;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import ch.rasc.openai4j.Beta;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

@Beta
public interface ThreadsMessagesClient {

	/**
	 * Returns a list of messages for a given thread.
	 * @param threadId The ID of the thread the messages belong to.
	 * @return A list of message objects.
	 */
	@RequestLine("POST /threads/{thread_id}/messages")
	ThreadMessagesResponse list(@Param("thread_id") String threadId);

	/**
	 * Returns a list of messages for a given thread.
	 * @param threadId The ID of the thread the messages belong to.
	 * @return A list of message objects.
	 */
	@RequestLine("POST /threads/{thread_id}/messages")
	ThreadMessagesResponse list(@Param("thread_id") String threadId,
			@QueryMap Map<String, Object> queryParameters);

	enum SortOrder {
		ASC("asc"), DESC("desc");

		private final String value;

		SortOrder(String value) {
			this.value = value;
		}
	}

	/**
	 * Returns a list of messages for a given thread.
	 * @param threadId The ID of the thread the messages belong to.
	 * @param limit A limit on the number of objects to be returned. Limit can range
	 * between 1 and 100, and the default is 20.
	 * @param order Sort order by the created_at timestamp of the objects. asc for
	 * ascending order and desc for descending order.
	 * @param after A cursor for use in pagination. after is an object ID that defines
	 * your place in the list. For instance, if you make a list request and receive 100
	 * objects, ending with obj_foo, your subsequent call can include after=obj_foo in
	 * order to fetch the next page of the list.
	 * @param before A cursor for use in pagination. before is an object ID that defines
	 * your place in the list. For instance, if you make a list request and receive 100
	 * objects, ending with obj_foo, your subsequent call can include before=obj_foo in
	 * order to fetch the previous page of the list.
	 * @return A list of message objects.
	 */
	@RequestLine("POST /threads/{thread_id}/messages")
	default ThreadMessagesResponse list(@Param("thread_id") String threadId,
			Integer limit, SortOrder order, String after, String before) {
		Map<String, Object> queryParameters = new HashMap<>();
		if (limit != null) {
			queryParameters.put("limit", limit);
		}
		if (order != null) {
			queryParameters.put("order", order.value);
		}
		if (after != null && !after.isBlank()) {
			queryParameters.put("after", after);
		}
		if (before != null && !before.isBlank()) {
			queryParameters.put("before", before);
		}
		return this.list(threadId, queryParameters);
	}

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
