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
package ch.rasc.openai4j.assistants.files;

import ch.rasc.openai4j.Beta;
import ch.rasc.openai4j.common.DeletionStatus;
import ch.rasc.openai4j.common.ListRequest;
import ch.rasc.openai4j.common.ListResponse;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

import java.util.Map;
import java.util.function.Function;

@Beta
public interface AssistantsFilesClient {
	/**
	 * Create an assistant file by attaching a File to an assistant.
	 *
	 * @param assistantId The ID of the assistant for which to create a File.
	 * @return An assistant file object.
	 */
	@RequestLine("POST /assistants/{assistant_id}/files")
	@Headers("Content-Type: application/json")
	AssistantFile create(@Param("assistant_id") String assistantId,
			AssistantFileCreateRequest request);

	/**
	 * Create an assistant file by attaching a File to an assistant.
	 *
	 * @param assistantId The ID of the assistant for which to create a File.
	 * @return An assistant file object.
	 */
	default AssistantFile create(String assistantId,
			Function<AssistantFileCreateRequest.Builder, AssistantFileCreateRequest.Builder> fn) {
		return this.create(assistantId,
				fn.apply(AssistantFileCreateRequest.builder()).build());
	}

	/**
	 * Retrieves an assistant file.
	 *
	 * @param assistantId The ID of the assistant who the file belongs to.
	 * @param fileId The ID of the file we're getting.
	 * @return The assistant file object matching the specified ID.
	 */
	@RequestLine("GET /assistants/{assistant_id}/files/{file_id}")
	AssistantFile retrieve(@Param("assistant_id") String assistantId,
			@Param("file_id") String fileId);

	/**
	 * Delete an assistant file.
	 *
	 * @param assistantId The ID of the assistant who the file belongs to.
	 * @param fileId The ID of the file to delete.
	 * @return Deletion status
	 */
	@RequestLine("DELETE /assistants/{assistant_id}/files/{file_id}")
	DeletionStatus delete(@Param("assistant_id") String assistantId,
			@Param("file_id") String fileId);

	/**
	 * Returns a list of assistant files.
	 *
	 * @param assistantId The ID of the assistant who the file belongs to.
	 * @return A list of assistant file objects.
	 */
	@RequestLine("GET /assistants/{assistant_id}/files")
	ListResponse<AssistantFile> list(@Param("assistant_id") String assistantId);

	/**
	 * Returns a list of assistant files.
	 *
	 * @param assistantId The ID of the assistant who the file belongs to.
	 * @return A list of assistant file objects.
	 */
	@RequestLine("GET /assistants/{assistant_id}/files")
	ListResponse<AssistantFile> list(@Param("assistant_id") String assistantId,
			@QueryMap Map<String, Object> queryParameters);

	/**
	 * Returns a list of assistant files.
	 *
	 * @param assistantId The ID of the assistant who the file belongs to.
	 * @param request A list request object with configuration for paging and ordering
	 * @return A list of assistant file objects.
	 */
	default ListResponse<AssistantFile> list(String assistantId, ListRequest request) {
		return this.list(assistantId, request.toMap());
	}

	/**
	 * Returns a list of assistant files.
	 *
	 * @param assistantId The ID of the assistant who the file belongs to.
	 * @param request A list request object with configuration for paging and ordering
	 * @return A list of assistant file objects.
	 */
	default ListResponse<AssistantFile> list(String assistantId,
			Function<ListRequest.Builder, ListRequest.Builder> fn) {
		return this.list(assistantId, fn.apply(ListRequest.builder()).build());
	}
}
