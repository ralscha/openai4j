package ch.rasc.openai4j.finetuningjobs;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonSerialize(as = ImmutableFineTuningJobCreateRequest.class)
@JsonInclude(Include.NON_EMPTY)
@Value.Enclosing
public interface FineTuningJobCreateRequest {

	/**
	 * The name of the model to fine-tune. You can select one of the supported models.
	 */
	String model();

	/**
	 * The ID of an uploaded file that contains training data. Your dataset must be
	 * formatted as a JSONL file. Additionally, you must upload your file with the purpose
	 * fine-tune.
	 */
	@JsonProperty("training_file")
	String trainingFile();

	Hyperparameters hyperparameters();

	@Value.Immutable
	@Value.Style(visibility = ImplementationVisibility.PACKAGE)
	@JsonSerialize(as = ImmutableFineTuningJobCreateRequest.Hyperparameters.class)
	@JsonInclude(Include.NON_EMPTY)
	interface Hyperparameters {
		/**
		 * Number of examples in each batch. A larger batch size means that model
		 * parameters are updated less frequently, but with lower variance. Defaults to
		 * auto
		 */
		@JsonProperty("batch_size")
		@Nullable
		BatchSize batchSize();

		class BatchSize {
			private final Object value;

			public BatchSize(Object value) {
				this.value = value;
			}

			public static BatchSize auto() {
				return new BatchSize("auto");
			}

			public static BatchSize of(int value) {
				return new BatchSize(value);
			}

			@JsonValue
			public Object value() {
				return this.value;
			}
		}

		/**
		 * Scaling factor for the learning rate. A smaller learning rate may be useful to
		 * avoid overfitting. Defaults to auto
		 */
		@JsonProperty("learning_rate_multiplier")
		@Nullable
		LearningRateMultiplier learningRateMultiplier();

		class LearningRateMultiplier {
			private final Object value;

			public LearningRateMultiplier(Object value) {
				this.value = value;
			}

			public static LearningRateMultiplier auto() {
				return new LearningRateMultiplier("auto");
			}

			public static LearningRateMultiplier of(double value) {
				return new LearningRateMultiplier(value);
			}

			@JsonValue
			public Object value() {
				return this.value;
			}
		}

		/**
		 * The number of epochs to train the model for. An epoch refers to one full cycle
		 * through the training dataset. Defaults to auto
		 */
		@JsonProperty("n_epochs")
		@Nullable
		Epochs nEpochs();

		class Epochs {
			private final Object value;

			public Epochs(Object value) {
				this.value = value;
			}

			public static Epochs auto() {
				return new Epochs("auto");
			}

			public static Epochs of(int value) {
				return new Epochs(value);
			}

			@JsonValue
			public Object value() {
				return this.value;
			}
		}

	}

	/**
	 * A string of up to 18 characters that will be added to your fine-tuned model name.
	 * For example, a suffix of "custom-model-name" would produce a model name like
	 * ft:gpt-3.5-turbo:openai:custom-model-name:7p4lURel.
	 */
	@Nullable
	String suffix();

	/**
	 * The ID of an uploaded file that contains validation data. If you provide this file,
	 * the data is used to generate validation metrics periodically during fine-tuning.
	 * These metrics can be viewed in the fine-tuning results file. The same data should
	 * not be present in both train and validation files. Your dataset must be formatted
	 * as a JSONL file. You must upload your file with the purpose fine-tune.
	 */
	@JsonProperty("validation_file")
	@Nullable
	String validationFile();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableFineTuningJobCreateRequest.Builder {
	}
}
