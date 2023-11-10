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
package ch.rasc.openai4j.files;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.Nullable;

/**
 * The File object represents a document that has been uploaded to OpenAI.
 */
@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableFileObject.class)
public interface FileObject {

	enum Status {
		UPLOADED("uploaded"), PROCESSED("processed"), ERROR("error"), DELETED("deleted");

		private final String value;

		Status(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}

		boolean isTerminal() {
			return this == PROCESSED || this == ERROR || this == DELETED;
		}
	}

	/**
	 * The file identifier, which can be referenced in the API endpoints.
	 */
	String id();

	/**
	 * The size of the file, in bytes.
	 */
	Integer bytes();

	/**
	 * The Unix timestamp (in seconds) for when the file was created.
	 */
	@JsonProperty("created_at")
	Integer createdAt();

	/**
	 * The name of the file.
	 */
	String filename();

	/**
	 * The object type, which is always file.
	 */
	String object();

	/**
	 * The intended purpose of the file. Supported values are fine-tune,
	 * fine-tune-results, assistants, and assistants_output.
	 */
	Purpose purpose();

	/**
	 * The current status of the file, which can be either uploaded, processed, or error.
	 */
	Status status();

	/**
	 * For details on why a fine-tuning training file failed validation, see the error
	 * field on fine_tuning.job.
	 */
	@JsonProperty("status_details")
	@Nullable
	String statusDetails();
}
