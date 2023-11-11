/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.rasc.openai4j.finetuningjobs;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import ch.rasc.openai4j.Nullable;
import ch.rasc.openai4j.common.Error;
import ch.rasc.openai4j.finetuningjobs.FineTuningJob.HyperParameters;
import ch.rasc.openai4j.finetuningjobs.FineTuningJob.Status;

/**
 * The fine_tuning.job object represents a fine-tuning job that has been created through
 * the API.
 */
public record FineTuningJob(String id, @JsonProperty("created_at") int createdAt,
		@Nullable Error error,
		@JsonProperty("fine_tuned_model") @Nullable String fineTunedModel,
		@JsonProperty("finished_at") @Nullable Integer finishedAt,
		HyperParameters hyperparameters, String model, String object,
		@JsonProperty("organization_id") String organizationId,
		@JsonProperty("result_files") String[] resultFiles, Status status,
		@JsonProperty("trained_tokens") @Nullable Integer trainedTokens,
		@JsonProperty("training_file") String trainingFile,
		@JsonProperty("validation_file") @Nullable String validationFile) {

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

	public record HyperParameters(@JsonProperty("n_epochs") int nEpochs,
			@JsonProperty("batch_size") int batchSize,
			@JsonProperty("learning_rate_multiplier") double learningRateMultiplier) {
	}

}
