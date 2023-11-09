package ch.rasc.openai4j.threads.runs.steps;

import java.util.Map;

import ch.rasc.openai4j.common.ListRequest;
import ch.rasc.openai4j.common.ListResponse;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

public interface ThreadsRunsStepsClient {

	/**
	 * Retrieves a run step.
	 * @param threadId The ID of the thread to which the run and run step belongs.
	 * @param runId The ID of the run to which the run step belongs.
	 * @param stepId The ID of the run step to retrieve.
	 * @return The run step object matching the specified ID.
	 */
	@RequestLine("GET /threads/{thread_id}/runs/{run_id}/steps/{step_id}")
	ThreadRunStepObject retrieve(@Param("thread_id") String threadId,
			@Param("run_id") String runId, @Param("step_id") String stepId);

	/**
	 * Returns a list of run steps belonging to a run.
	 * @param threadId The ID of the thread to which the run and run step belongs.
	 * @param runId The ID of the run to which the run step belongs.
	 * @return A list of run step objects.
	 */
	@RequestLine("GET /threads/{thread_id}/runs/{run_id}/steps")
	ListResponse<ThreadRunStepObject> list(@Param("thread_id") String threadId,
			@Param("run_id") String runId);

	/**
	 * Returns a list of run steps belonging to a run.
	 * @param threadId The ID of the thread to which the run and run step belongs.
	 * @param runId The ID of the run to which the run step belongs.
	 * @return A list of run step objects.
	 */
	@RequestLine("GET /threads/{thread_id}/runs/{run_id}/steps")
	ListResponse<ThreadRunStepObject> list(@Param("thread_id") String threadId,
			@Param("run_id") String runId, @QueryMap Map<String, Object> queryParameters);

	/**
	 * Returns a list of run steps belonging to a run.
	 * @param threadId The ID of the thread to which the run and run step belongs.
	 * @param runId The ID of the run to which the run step belongs.
	 * @param request A list request object with configuration for paging and ordering
	 * @return A list of run step objects.
	 */
	default ListResponse<ThreadRunStepObject> list(@Param("thread_id") String threadId,
			@Param("run_id") String runId, ListRequest request) {
		return this.list(threadId, runId, request.toMap());
	}
}
