package ch.rasc.openai4j.assistants.files;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import ch.rasc.openai4j.Beta;
import ch.rasc.openai4j.common.DeletionStatus;
import ch.rasc.openai4j.common.ListResponse;
import ch.rasc.openai4j.common.SortOrder;
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
	 * @return A list of assistant file objects.
	 */
	default ListResponse<AssistantFileObject> list(String assistantId, Integer limit,
			SortOrder order, String after, String before) {
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
		return this.list(assistantId, queryParameters);
	}
}
