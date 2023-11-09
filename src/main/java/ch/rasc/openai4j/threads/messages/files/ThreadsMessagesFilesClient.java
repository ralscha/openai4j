package ch.rasc.openai4j.threads.messages.files;

import java.util.Map;

import ch.rasc.openai4j.Beta;
import ch.rasc.openai4j.common.ListRequest;
import ch.rasc.openai4j.common.ListResponse;
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
	 * @param request A list request object with configuration for paging and ordering
	 * @return A list of message file objects.
	 */
	default ListResponse<ThreadMessageFileObject> list(String threadId, String messageId,
			ListRequest request) {
		return this.list(threadId, messageId, request.toMap());
	}
}
