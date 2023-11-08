package ch.rasc.openai4j.file;

import java.nio.file.Path;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public interface FileUploadRequest {
	/**
	 * The File object to be uploaded.
	 */
	Path file();

	/**
	 * The intended purpose of the uploaded file.
	 *
	 * Use "fine-tune" for Fine-tuning and "assistants" for Assistants and Messages. This
	 * allows us to validate the format of the uploaded file is correct for fine-tuning.
	 */
	Purpose purpose();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableFileUploadRequest.Builder {
	}
}
