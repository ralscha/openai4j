package ch.rasc.openai4j.file;

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
		UPLOADED("uploaded"), PROCESSED("processed"), ERROR("error");

		private final String value;

		Status(String value) {
			this.value = value;
		}

		@JsonValue
		public String toValue() {
			return this.value;
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
