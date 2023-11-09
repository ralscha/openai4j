package ch.rasc.openai4j.assistants.files;

import java.util.Map;
import java.util.function.Function;

import ch.rasc.openai4j.Beta;
import ch.rasc.openai4j.common.DeletionStatus;
import ch.rasc.openai4j.common.ListRequest;
import ch.rasc.openai4j.common.ListResponse;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

@Beta
public interface AssistantsFilesClient {
	/**
	 * Create an assistant file by attaching a File to an assistant.
	 * @param The ID of the assistant for which to create a File.
	 * @return An assistant file object.
	 */
	@RequestLine("POST /assistants/{assistant_id}/files")
	@Headers("Content-Type: application/json")
	AssistantFileObject create(@Param("assistant_id") String assistantId,
			AssistantFileCreateRequest request);

	/**
	 * Create an assistant file by attaching a File to an assistant.
	 * @param The ID of the assistant for which to create a File.
	 * @return An assistant file object.
	 */
	default AssistantFileObject create(String assistantId,
			Function<AssistantFileCreateRequest.Builder, AssistantFileCreateRequest.Builder> fn) {
		return this.create(assistantId,
				fn.apply(AssistantFileCreateRequest.builder()).build());
	}

	/**
	 * Retrieves an assistant file.
	 * @param assistantId The ID of the assistant who the file belongs to.
	 * @param fileId The ID of the file we're getting.
	 * @return The assistant file object matching the specified ID.
	 */
	@RequestLine("GET /assistants/{assistant_id}/files/{file_id}")
	AssistantFileObject retrieve(@Param("assistant_id") String assistantId,
			@Param("file_id") String fileId);

	/**
	 * Delete an assistant file.
	 * @param assistantId The ID of the assistant who the file belongs to.
	 * @param fileId The ID of the file to delete.
	 * @return Deletion status
	 */
	@RequestLine("DELETE /assistants/{assistant_id}/files/{file_id}")
	DeletionStatus delete(@Param("assistant_id") String assistantId,
			@Param("file_id") String fileId);

	/**
	 * Returns a list of assistant files.
	 * @param assistantId The ID of the assistant who the file belongs to.
	 * @return A list of assistant file objects.
	 */
	@RequestLine("GET /assistants/{assistant_id}/files")
	ListResponse<AssistantFileObject> list(@Param("assistant_id") String assistantId);

	/**
	 * Returns a list of assistant files.
	 * @param assistantId The ID of the assistant who the file belongs to.
	 * @return A list of assistant file objects.
	 */
	@RequestLine("GET /assistants/{assistant_id}/files")
	ListResponse<AssistantFileObject> list(@Param("assistant_id") String assistantId,
			@QueryMap Map<String, Object> queryParameters);

	/**
	 * Returns a list of assistant files.
	 * @param assistantId The ID of the assistant who the file belongs to.
	 * @param request A list request object with configuration for paging and ordering
	 * @return A list of assistant file objects.
	 */
	default ListResponse<AssistantFileObject> list(String assistantId,
			ListRequest request) {
		return this.list(assistantId, request.toMap());
	}
}
