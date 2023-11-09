package ch.rasc.openai4j.threads.messages.files;

import java.util.HashMap;
import java.util.Map;

import ch.rasc.openai4j.Beta;
import ch.rasc.openai4j.common.ListResponse;
import ch.rasc.openai4j.common.SortOrder;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

@Beta
public interface ThreadsMessagesFilesClient {

	/**
	 * Retrieves a message file.
	 * @param threadId The ID of the thread to which the message and File belong.
	 * @param messageId The ID of the message the file belongs to.
	 * @param fileId The ID of the file being retrieved.
	 * @return The message file object.
	 */
	@RequestLine("GET /threads/{thread_id}/messages/{message_id}/files/{file_id}")
	ThreadMessageFileObject retrieve(@Param("thread_id") String threadId,
			@Param("message_id") String messageId, @Param("file_id") String fileId);

	/**
	 * Returns a list of message files.
	 * @param threadId The ID of the thread to which the message and File belong.
	 * @param messageId The ID of the message the file belongs to.
	 * @return A list of message file objects.
	 */
	@RequestLine("GET /threads/{thread_id}/messages/{message_id}/files")
	ListResponse<ThreadMessageFileObject> list(@Param("thread_id") String threadId,
			@Param("message_id") String messageId);

	/**
	 * Returns a list of message files.
	 * @param threadId The ID of the thread to which the message and File belong.
	 * @param messageId The ID of the message the file belongs to.
	 * @return A list of message file objects.
	 */
	@RequestLine("GET /threads/{thread_id}/messages/{message_id}/files")
	ListResponse<ThreadMessageFileObject> list(@Param("thread_id") String threadId,
			@Param("message_id") String messageId,
			@QueryMap Map<String, Object> queryParameters);

	/**
	 * Returns a list of message files.
	 * @param threadId The ID of the thread to which the message and File belong.
	 * @param messageId The ID of the message the file belongs to.
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
	 * @return A list of message file objects.
	 */
	default ListResponse<ThreadMessageFileObject> list(String threadId, String messageId,
			Integer limit, SortOrder order, String after, String before) {
		Map<String, Object> queryParameters = new HashMap<>();
		if (limit != null) {
			queryParameters.put("limit", limit);
		}
		if (order != null) {
			queryParameters.put("order", order.value());
		}
		if (after != null && !after.isBlank()) {
			queryParameters.put("after", after);
		}
		if (before != null && !before.isBlank()) {
			queryParameters.put("before", before);
		}
		return this.list(threadId, messageId, queryParameters);
	}
}
