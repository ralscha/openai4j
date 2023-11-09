package ch.rasc.openai4j.finetuning;

import java.util.HashMap;
import java.util.Map;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

public interface FineTuningClient {

	/**
	 * Creates a job that fine-tunes a specified model from a given dataset.
	 *
	 * @return Fine-tuning job object.
	 */
	@RequestLine("POST /fine_tuning/jobs")
	FineTuningJobObject create(FineTuningJobCreateRequest request);

	/**
	 * List your organization's fine-tuning jobs
	 *
	 * @return A list of paginated fine-tuning job objects.
	 */
	@RequestLine("GET /fine_tuning/jobs")
	FineTuningJobResponse jobs();

	/**
	 * List your organization's fine-tuning jobs
	 *
	 * @return A list of paginated fine-tuning job objects.
	 */
	@RequestLine("GET /fine_tuning/jobs")
	FineTuningJobResponse jobs(@QueryMap Map<String, Object> queryParameters);

	/**
	 *
	 * List your organization's fine-tuning jobs
	 * @param after Identifier for the last job from the previous pagination request.
	 * Optional.
	 * @param limit Number of fine-tuning jobs to retrieve. Optional. Defaults to 20.
	 * @return A list of paginated fine-tuning job objects.
	 */
	default FineTuningJobResponse jobs(String after, Integer limit) {
		Map<String, Object> queryParameters = new HashMap<>();
		if (after != null && !after.isBlank()) {
			queryParameters.put("after", after);
		}
		if (limit != null) {
			queryParameters.put("limit", limit);
		}
		return this.jobs(queryParameters);
	}

	/**
	 * Get info about a fine-tuning job.
	 *
	 * @return The fine-tuning object with the given ID.
	 */
	@RequestLine("GET /fine_tuning/jobs/{fine_tuning_job_id}")
	FineTuningJobObject job(@Param("fine_tuning_job_id") String fineTuningJobId);

	/**
	 * Immediately cancel a fine-tune job.
	 *
	 * @return The cancelled fine-tuning object.
	 */
	@RequestLine("POST /fine_tuning/jobs/{fine_tuning_job_id}/cancel")
	FineTuningJobObject cancel(@Param("fine_tuning_job_id") String fineTuningJobId);

	/**
	 * Get status updates for a fine-tuning job.
	 *
	 * @return A list of fine-tuning event objects.
	 */
	@RequestLine("GET /fine_tuning/jobs/{fine_tuning_job_id}/events")
	FineTuningJobEventsResponse jobEvents(
			@Param("fine_tuning_job_id") String fineTuningJobId);

	/**
	 * Get status updates for a fine-tuning job.
	 *
	 * @return A list of fine-tuning event objects.
	 */
	@RequestLine("GET /fine_tuning/jobs/{fine_tuning_job_id}/events")
	FineTuningJobEventsResponse jobEvents(
			@Param("fine_tuning_job_id") String fineTuningJobId,
			@QueryMap Map<String, Object> queryParameters);

	/**
	 * Get status updates for a fine-tuning job.
	 * @param after Identifier for the last event from the previous pagination request.
	 * Optional.
	 * @param limit Number of events to retrieve. Optional. Defaults to 20.
	 * @return A list of fine-tuning event objects.
	 */
	default FineTuningJobEventsResponse jobEvents(String fineTuningJobId, String after,
			Integer limit) {
		Map<String, Object> queryParameters = new HashMap<>();
		if (after != null && !after.isBlank()) {
			queryParameters.put("after", after);
		}
		if (limit != null) {
			queryParameters.put("limit", limit);
		}
		return this.jobEvents(fineTuningJobId, queryParameters);
	}
}
