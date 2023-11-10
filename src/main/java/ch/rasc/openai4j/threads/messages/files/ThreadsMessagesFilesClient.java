/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
	ThreadMessageFile retrieve(@Param("thread_id") String threadId,
			@Param("message_id") String messageId, @Param("file_id") String fileId);

	/**
	 * Returns a list of message files.
	 * @param threadId The ID of the thread to which the message and File belong.
	 * @param messageId The ID of the message the file belongs to.
	 * @return A list of message file objects.
	 */
	@RequestLine("GET /threads/{thread_id}/messages/{message_id}/files")
	ListResponse<ThreadMessageFile> list(@Param("thread_id") String threadId,
			@Param("message_id") String messageId);

	/**
	 * Returns a list of message files.
	 * @param threadId The ID of the thread to which the message and File belong.
	 * @param messageId The ID of the message the file belongs to.
	 * @return A list of message file objects.
	 */
	@RequestLine("GET /threads/{thread_id}/messages/{message_id}/files")
	ListResponse<ThreadMessageFile> list(@Param("thread_id") String threadId,
			@Param("message_id") String messageId,
			@QueryMap Map<String, Object> queryParameters);

	/**
	 * Returns a list of message files.
	 * @param threadId The ID of the thread to which the message and File belong.
	 * @param messageId The ID of the message the file belongs to.
	 * @param request A list request object with configuration for paging and ordering
	 * @return A list of message file objects.
	 */
	default ListResponse<ThreadMessageFile> list(String threadId, String messageId,
			ListRequest request) {
		return this.list(threadId, messageId, request.toMap());
	}
}
