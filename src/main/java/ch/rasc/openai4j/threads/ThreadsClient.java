package ch.rasc.openai4j.threads;

import java.util.Map;
import java.util.function.Function;

import ch.rasc.openai4j.Beta;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

@Beta
public interface ThreadsClient {

	/**
	 * Create a thread.
	 * 
	 * @return A thread object.
	 */
	@RequestLine("POST /threads")
	@Headers("Content-Type: application/json")
	ThreadObject create(ThreadCreateRequest request);
	
	/**
	 * Create a thread.
	 * 
	 * @return A thread object.
	 */
	default ThreadObject create(
			Function<ThreadCreateRequest.Builder, ThreadCreateRequest.Builder> fn) {
		return this.create(fn.apply(ThreadCreateRequest.builder()).build());
	}
	
	/**
	 * Retrieves a thread.
	 * 
	 * @return The thread object matching the specified ID.
	 */
	@RequestLine("GET /threads/{thread_id}")
	ThreadObject retrieve(@Param("thread_id") String threadId);

	/**
	 * Modifies a thread.
	 * 
	 * @return The modified thread object matching the specified ID.
	 */
	@RequestLine("POST /threads/{thread_id}")
	@Headers("Content-Type: application/json")
	ThreadObject update(@Param("thread_id") String threadId,
			ThreadUpdateRequest request);

	/**
	 * Delete a thread
	 *
	 * @return Deletion status.
	 */
	@RequestLine("DELETE /threads/{thread_id}")
	ThreadDeletionResponse delete(@Param("thread_id") String threadId);

}
