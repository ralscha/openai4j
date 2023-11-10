package ch.rasc.openai4j.finetuningjobs;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

public interface FineTuningJobsClient {

	/**
	 * Creates a job that fine-tunes a specified model from a given dataset.
	 *
	 * @return Fine-tuning job object.
	 */
	@RequestLine("POST /fine_tuning/jobs")
	FineTuningJob create(FineTuningJobCreateRequest request);

	/**
	 * Creates a job that fine-tunes a specified model from a given dataset.
	 *
	 * @return Fine-tuning job object.
	 */
	default FineTuningJob create(
			Function<FineTuningJobCreateRequest.Builder, FineTuningJobCreateRequest.Builder> fn) {
		return this.create(fn.apply(FineTuningJobCreateRequest.builder()).build());
	}

	/**
	 * List your organization's fine-tuning jobs
	 *
	 * @return A list of paginated fine-tuning job objects.
	 */
	@RequestLine("GET /fine_tuning/jobs")
	FineTuningJobResponse list();

	/**
	 * List your organization's fine-tuning jobs
	 *
	 * @return A list of paginated fine-tuning job objects.
	 */
	@RequestLine("GET /fine_tuning/jobs")
	FineTuningJobResponse list(@QueryMap Map<String, Object> queryParameters);

	/**
	 *
	 * List your organization's fine-tuning jobs
	 * @param after Identifier for the last job from the previous pagination request.
	 * Optional.
	 * @param limit Number of fine-tuning jobs to retrieve. Optional. Defaults to 20.
	 * @return A list of paginated fine-tuning job objects.
	 */
	default FineTuningJobResponse list(String after, Integer limit) {
		Map<String, Object> queryParameters = new HashMap<>();
		if (after != null && !after.isBlank()) {
			queryParameters.put("after", after);
		}
		if (limit != null) {
			queryParameters.put("limit", limit);
		}
		return this.list(queryParameters);
	}

	/**
	 * Get info about a fine-tuning job.
	 *
	 * @return The fine-tuning object with the given ID.
	 */
	@RequestLine("GET /fine_tuning/jobs/{fine_tuning_job_id}")
	FineTuningJob retrieve(@Param("fine_tuning_job_id") String fineTuningJobId);

	/**
	 * Immediately cancel a fine-tune job.
	 *
	 * @return The cancelled fine-tuning object.
	 */
	@RequestLine("POST /fine_tuning/jobs/{fine_tuning_job_id}/cancel")
	FineTuningJob cancel(@Param("fine_tuning_job_id") String fineTuningJobId);

	/**
	 * Get status updates for a fine-tuning job.
	 *
	 * @return A list of fine-tuning event objects.
	 */
	@RequestLine("GET /fine_tuning/jobs/{fine_tuning_job_id}/events")
	FineTuningJobEventsResponse listEvents(
			@Param("fine_tuning_job_id") String fineTuningJobId);

	/**
	 * Get status updates for a fine-tuning job.
	 *
	 * @return A list of fine-tuning event objects.
	 */
	@RequestLine("GET /fine_tuning/jobs/{fine_tuning_job_id}/events")
	FineTuningJobEventsResponse listEvents(
			@Param("fine_tuning_job_id") String fineTuningJobId,
			@QueryMap Map<String, Object> queryParameters);

	/**
	 * Get status updates for a fine-tuning job.
	 * @param after Identifier for the last event from the previous pagination request.
	 * Optional.
	 * @param limit Number of events to retrieve. Optional. Defaults to 20.
	 * @return A list of fine-tuning event objects.
	 */
	default FineTuningJobEventsResponse listEvents(String fineTuningJobId, String after,
			Integer limit) {
		Map<String, Object> queryParameters = new HashMap<>();
		if (after != null && !after.isBlank()) {
			queryParameters.put("after", after);
		}
		if (limit != null) {
			queryParameters.put("limit", limit);
		}
		return this.listEvents(fineTuningJobId, queryParameters);
	}
}
