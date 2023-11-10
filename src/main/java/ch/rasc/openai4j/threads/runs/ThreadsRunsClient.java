package ch.rasc.openai4j.threads.runs;

import java.util.Map;
import java.util.function.Function;

import ch.rasc.openai4j.Beta;
import ch.rasc.openai4j.common.ListRequest;
import ch.rasc.openai4j.common.ListResponse;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

@Beta
public interface ThreadsRunsClient {

	/**
	 * Create a run.
	 * @param threadId The ID of the thread to run.
	 * @return A run object.
	 */
	@RequestLine("POST /threads/{thread_id}/runs")
	@Headers("Content-Type: application/json")
	ThreadRunObject create(@Param("thread_id") String threadId,
			ThreadRunCreateRequest request);

	/**
	 * Create a run.
	 * @param threadId The ID of the thread to run.
	 * @return A run object.
	 */
	default ThreadRunObject create(@Param("thread_id") String threadId,
			Function<ThreadRunCreateRequest.Builder, ThreadRunCreateRequest.Builder> fn) {
		return this.create(threadId, fn.apply(ThreadRunCreateRequest.builder()).build());
	}

	/**
	 * Retrieves a run.
	 * @param threadId The ID of the thread that was run.
	 * @param runId The ID of the run to retrieve.
	 * @return The run object matching the specified ID.
	 */
	@RequestLine("GET /threads/{thread_id}/runs/{run_id}")
	ThreadRunObject retrieve(@Param("thread_id") String threadId,
			@Param("run_id") String runId);

	/**
	 * Modifies a run.
	 *
	 * @return The modified run object matching the specified ID.
	 */
	@RequestLine("POST /threads/{thread_id}/runs/{run_id}")
	@Headers("Content-Type: application/json")
	ThreadRunObject update(@Param("thread_id") String threadId,
			@Param("run_id") String runId, ThreadRunUpdateRequest request);

	/**
	 * Cancels a run that is in_progress.
	 *
	 * @return The modified run object matching the specified ID.
	 */
	@RequestLine("POST /threads/{thread_id}/runs/{run_id}/cancel")
	@Headers("Content-Type: application/json")
	ThreadRunObject cancel(@Param("thread_id") String threadId,
			@Param("run_id") String runId);

	/**
	 * Returns a list of runs belonging to a thread.
	 * @param threadId The ID of the thread the run belongs to.
	 * @return A list of run objects.
	 */
	@RequestLine("GET /threads/{thread_id}/runs")
	ListResponse<ThreadRunObject> list(@Param("thread_id") String threadId);

	/**
	 * Returns a list of runs belonging to a thread.
	 * @param threadId The ID of the thread the run belongs to.
	 * @return A list of run objects.
	 */
	@RequestLine("GET /threads/{thread_id}/runs")
	ListResponse<ThreadRunObject> list(@Param("thread_id") String threadId,
			@QueryMap Map<String, Object> queryParameters);

	/**
	 * Returns a list of runs belonging to a thread.
	 * @param threadId The ID of the thread the run belongs to.
	 * @param request A list request object with configuration for paging and ordering
	 * @return A list of run objects.
	 */
	default ListResponse<ThreadRunObject> list(@Param("thread_id") String threadId,
			ListRequest request) {
		return this.list(threadId, request.toMap());
	}

	/**
	 * When a run has the status: "requires_action" and required_action.type is
	 * submit_tool_outputs, this endpoint can be used to submit the outputs from the tool
	 * calls once they're all completed. All outputs must be submitted in a single
	 * request.
	 * @param threadId The ID of the thread to which this run belongs.
	 * @param runId The ID of the run that requires the tool output submission.
	 * @return The modified run object matching the specified ID.
	 */
	@RequestLine("POST /threads/{thread_id}/runs/{run_id}/submit_tool_outputs")
	ThreadRunObject submitToolOutputs(@Param("thread_id") String threadId,
			@Param("run_id") String runId, ThreadRunSubmitToolOutputsRequest request);

	/**
	 * When a run has the status: "requires_action" and required_action.type is
	 * submit_tool_outputs, this endpoint can be used to submit the outputs from the tool
	 * calls once they're all completed. All outputs must be submitted in a single
	 * request.
	 * @param threadId The ID of the thread to which this run belongs.
	 * @param runId The ID of the run that requires the tool output submission.
	 * @return The modified run object matching the specified ID.
	 */
	default ThreadRunObject submitToolOutputs(@Param("thread_id") String threadId,
			@Param("run_id") String runId,
			Function<ThreadRunSubmitToolOutputsRequest.Builder, ThreadRunSubmitToolOutputsRequest.Builder> fn) {
		return this.submitToolOutputs(threadId, runId,
				fn.apply(ThreadRunSubmitToolOutputsRequest.builder()).build());
	}

}
