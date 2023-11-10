package ch.rasc.openai4j.threads;

import java.util.function.Function;

import ch.rasc.openai4j.Beta;
import ch.rasc.openai4j.common.DeletionStatus;
import ch.rasc.openai4j.threads.runs.ThreadRun;
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
	Thread create(ThreadCreateRequest request);

	/**
	 * Create a thread.
	 *
	 * @return A thread object.
	 */
	default Thread create(
			Function<ThreadCreateRequest.Builder, ThreadCreateRequest.Builder> fn) {
		return this.create(fn.apply(ThreadCreateRequest.builder()).build());
	}

	/**
	 * Create a thread and run it in one request.
	 *
	 * @return A run object.
	 */
	@RequestLine("POST /threads/runs")
	@Headers("Content-Type: application/json")
	ThreadRun createAndRun(ThreadCreateRunCreateRequest request);

	/**
	 * Create a thread and run it in one request.
	 *
	 * @return A run object.
	 */
	default ThreadRun createAndRun(
			Function<ThreadCreateRunCreateRequest.Builder, ThreadCreateRunCreateRequest.Builder> fn) {
		return this
				.createAndRun(fn.apply(ThreadCreateRunCreateRequest.builder()).build());
	}

	/**
	 * Retrieves a thread.
	 *
	 * @return The thread object matching the specified ID.
	 */
	@RequestLine("GET /threads/{thread_id}")
	Thread retrieve(@Param("thread_id") String threadId);

	/**
	 * Modifies a thread.
	 *
	 * @return The modified thread object matching the specified ID.
	 */
	@RequestLine("POST /threads/{thread_id}")
	@Headers("Content-Type: application/json")
	Thread update(@Param("thread_id") String threadId, ThreadUpdateRequest request);

	/**
	 * Delete a thread
	 *
	 * @return Deletion status.
	 */
	@RequestLine("DELETE /threads/{thread_id}")
	DeletionStatus delete(@Param("thread_id") String threadId);

}
