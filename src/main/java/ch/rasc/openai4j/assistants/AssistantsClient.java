package ch.rasc.openai4j.assistants;

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
public interface AssistantsClient {

	/**
	 * Create an assistant with a model and instructions.
	 *
	 * @return An assistant object.
	 */
	@RequestLine("POST /assistants")
	@Headers("Content-Type: application/json")
	AssistantObject create(AssistantCreateRequest request);

	/**
	 * Create an assistant with a model and instructions.
	 *
	 * @return An assistant object.
	 */
	default AssistantObject create(
			Function<AssistantCreateRequest.Builder, AssistantCreateRequest.Builder> fn) {
		return this.create(fn.apply(AssistantCreateRequest.builder()).build());
	}

	/**
	 * Retrieves an assistant.
	 *
	 * @return The assistant object matching the specified ID.
	 */
	@RequestLine("GET /assistants/{assistant_id}")
	AssistantObject retrieve(@Param("assistant_id") String assistantId);

	/**
	 * Modifies an assistant.
	 *
	 * @return The modified assistant object.
	 */
	@RequestLine("POST /assistants/{assistant_id}")
	@Headers("Content-Type: application/json")
	AssistantObject update(@Param("assistant_id") String assistantId,
			AssistantUpdateRequest request);

	/**
	 * Modifies an assistant.
	 *
	 * @return The modified assistant object.
	 */
	default AssistantObject update(@Param("assistant_id") String assistantId,
			Function<AssistantUpdateRequest.Builder, AssistantUpdateRequest.Builder> fn) {
		return this.update(assistantId,
				fn.apply(AssistantUpdateRequest.builder()).build());
	}

	/**
	 * Delete an assistant.
	 *
	 * @return Deletion status
	 */
	@RequestLine("DELETE /assistants/{assistant_id}")
	DeletionStatus delete(@Param("assistant_id") String assistantId);

	/**
	 * Returns a list of assistants.
	 *
	 * @return A list of assistant objects.
	 */
	@RequestLine("GET /assistants")
	ListResponse<AssistantObject> list();

	/**
	 * Returns a list of assistants.
	 *
	 * @return A list of assistant objects.
	 */
	@RequestLine("GET /assistants")
	ListResponse<AssistantObject> list(@QueryMap Map<String, Object> queryParameters);

	/**
	 * Returns a list of assistants.
	 *
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
	 * @return A list of assistant objects.
	 */
	default ListResponse<AssistantObject> list(Integer limit, SortOrder order, String after,
			String before) {
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
		return this.list(queryParameters);
	}
}
