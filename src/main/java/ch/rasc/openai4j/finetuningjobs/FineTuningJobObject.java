package ch.rasc.openai4j.finetuningjobs;

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.Nullable;

/**
 * The fine_tuning.job object represents a fine-tuning job that has been created through
 * the API.
 */
@Value.Immutable(builder = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE,
		allParameters = true)
@JsonDeserialize(as = ImmutableFineTuningJobObject.class)
@Value.Enclosing
public interface FineTuningJobObject {

	/**
	 * The object identifier, which can be referenced in the API endpoints.
	 */
	String id();

	/**
	 * The Unix timestamp (in seconds) for when the fine-tuning job was created.
	 */
	@JsonProperty("created_at")
	int createdAt();

	/**
	 * For fine-tuning jobs that have failed, this will contain more information on the
	 * cause of the failure.
	 */
	@Nullable
	Error error();

	@Value.Immutable(builder = false)
	@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE,
			allParameters = true)
	@JsonDeserialize(as = ImmutableFineTuningJobObject.Error.class)
	interface Error {
		/**
		 * A machine-readable error code.
		 */
		String code();

		/**
		 * A human-readable error message.
		 */
		String message();

		/**
		 * The parameter that was invalid, usually training_file or validation_file. This
		 * field will be null if the failure was not parameter-specific.
		 */
		@Nullable
		String param();
	}

	/**
	 * The name of the fine-tuned model that is being created. The value will be null if
	 * the fine-tuning job is still running.
	 */
	@JsonProperty("fine_tuned_model")
	@Nullable
	String fineTunedModel();

	/**
	 * The Unix timestamp (in seconds) for when the fine-tuning job was finished. The
	 * value will be null if the fine-tuning job is still running.
	 */
	@JsonProperty("finished_at")
	@Nullable
	Integer finishedAt();

	/**
	 * The hyperparameters used for the fine-tuning job. See the fine-tuning guide for
	 * more details.
	 */
	HyperParameters hyperparameters();

	@Value.Immutable(builder = false)
	@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE,
			allParameters = true)
	@JsonDeserialize(as = ImmutableFineTuningJobObject.HyperParameters.class)
	interface HyperParameters {
		/**
		 * The number of epochs to train the model for. An epoch refers to one full cycle
		 * through the training dataset. "auto" decides the optimal number of epochs based
		 * on the size of the dataset. If setting the number manually, we support any
		 * number between 1 and 50 epochs.
		 */
		@JsonProperty("n_epochs")
		int nEpochs();

		@JsonProperty("batch_size")
		int batchSize();

		@JsonProperty("learning_rate_multiplier")
		double learningRateMultiplier();
	}

	/**
	 * The base model that is being fine-tuned.
	 */
	String model();

	/**
	 * The object type, which is always "fine_tuning.job".
	 */
	String object();

	/**
	 * The organization that owns the fine-tuning job.
	 */
	@JsonProperty("organization_id")
	String organizationId();

	/**
	 * The compiled results file ID(s) for the fine-tuning job. You can retrieve the
	 * results with the Files API.
	 */
	@JsonProperty("result_files")
	String[] resultFiles();

	enum Status {
		VALIDATING_FILES("validating_files"), QUEUED("queued"), RUNNING("running"),
		SUCCEEDED("succeeded"), FAILED("failed"), CANCELLED("cancelled");

		private final String value;

		Status(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}
	}

	/**
	 * The current status of the fine-tuning job.
	 */
	Status status();

	/**
	 * The total number of billable tokens processed by this fine-tuning job. The value
	 * will be null if the fine-tuning job is still running.
	 */
	@JsonProperty("trained_tokens")
	@Nullable
	Integer trainedTokens();

	/**
	 * The file ID used for training. You can retrieve the training data with the Files
	 * API.
	 */
	@JsonProperty("training_file")
	String trainingFile();

	/**
	 * The file ID used for validation. You can retrieve the validation results with the
	 * Files API.
	 */
	@JsonProperty("validation_file")
	@Nullable
	String validationFile();

}
