package ch.rasc.openai4j.finetuningjobs;

import com.fasterxml.jackson.annotation.JsonProperty;

public record FineTuningJobCheckpoint(String id,
		@JsonProperty("created_at") int createdAt,
		@JsonProperty("fine_tuned_model_checkpoint") String fineTunedModelCheckpoint,
		@JsonProperty("step_number") int stepNumber, Metrics metrics,
		@JsonProperty("fine_tuning_job_id") String fineTuningJobId, String object) {

	/**
	 * The checkpoint identifier, which can be referenced in the API endpoints.
	 */
	@Override
	public String id() {
		return this.id;
	}

	/**
	 * The Unix timestamp (in seconds) for when the checkpoint was created.
	 */
	@Override
	public int createdAt() {
		return this.createdAt;
	}

	/**
	 * The name of the fine-tuned checkpoint model that is created.
	 */
	@Override
	public String fineTunedModelCheckpoint() {
		return this.fineTunedModelCheckpoint;
	}

	/**
	 * The step number that the checkpoint was created at.
	 */
	@Override
	public int stepNumber() {
		return this.stepNumber;
	}

	/**
	 * Metrics at the step number during the fine-tuning job.
	 */
	@Override
	public Metrics metrics() {
		return this.metrics;
	}

	/**
	 * The name of the fine-tuning job that this checkpoint was created from.
	 */
	@Override
	public String fineTuningJobId() {
		return this.fineTuningJobId;
	}

	/**
	 * The object type, which is always "fine_tuning.job.checkpoint".
	 */
	@Override
	public String object() {
		return this.object;
	}

	public record Metrics(int step, @JsonProperty("train_loss") double trainLoss,
			@JsonProperty("train_mean_token_accuracy") double trainMeanTokenAccuracy,
			@JsonProperty("valid_loss") double validLoss,
			@JsonProperty("valid_mean_token_accuracy") double validMeanTokenAccuracy,
			@JsonProperty("full_valid_loss") double fullValidLoss,
			@JsonProperty("full_valid_mean_token_accuracy") double fullValidMeanTokenAccuracy) {
	}

}
