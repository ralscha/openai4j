package ch.rasc.openai4j.assistants;

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
	 * @param request A list request object with configuration for paging and ordering
	 * @return A list of assistant objects.
	 */
	default ListResponse<AssistantObject> list(ListRequest request) {
		return this.list(request.toMap());
	}
}
