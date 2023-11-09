package ch.rasc.openai4j.files;

import java.io.File;
import java.util.function.Function;

import ch.rasc.openai4j.common.DeletionStatus;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;

public interface FilesClient {

	/**
	 * Returns a list of files that belong to the user's organization.
	 *
	 * @return A list of File objects.
	 */
	@RequestLine("GET /files")
	FilesResponse list();

	/**
	 * Returns a list of files that belong to the user's organization with the given
	 * purpose.
	 *
	 * @return A list of File objects.
	 */
	@RequestLine("GET /files?purpose={purpose}")
	FilesResponse list(@Param("purpose") String purpose);

	/**
	 * Returns a list of files that belong to the user's organization with the given
	 * purpose.
	 *
	 * @return A list of File objects.
	 */
	default FilesResponse list(Purpose purpose) {
		return this.list(purpose.value());
	}
	
	/**
	 * Upload a file that can be used across various endpoints/features. The size of all
	 * the files uploaded by one organization can be up to 100 GB. The size of individual
	 * files for can be a maximum of 512MB. The Fine-tuning API only supports .jsonl
	 * files.
	 *
	 * @return The uploaded File object.
	 */
	default FileObject create(FileUploadRequest request) {
		return this.create(request.file().toFile(), request.purpose().value());
	}

	/**
	 * Upload a file that can be used across various endpoints/features. The size of all
	 * the files uploaded by one organization can be up to 100 GB. The size of individual
	 * files for can be a maximum of 512MB. The Fine-tuning API only supports .jsonl
	 * files.
	 *
	 * @return The uploaded File object.
	 */
	default FileObject create(
			Function<FileUploadRequest.Builder, FileUploadRequest.Builder> fn) {
		return this.create(fn.apply(FileUploadRequest.builder()).build());
	}

	/**
	 * Upload a file that can be used across various endpoints/features. The size of all
	 * the files uploaded by one organization can be up to 100 GB. The size of individual
	 * files for can be a maximum of 512MB. The Fine-tuning API only supports .jsonl
	 * files.
	 *
	 * @return The uploaded File object.
	 */
	@RequestLine("POST /files")
	@Headers("Content-Type: multipart/form-data")
	FileObject create(@Param("file") File file, @Param("purpose") String purpose);

	/**
	 * Delete a file
	 *
	 * @return Deletion status.
	 */
	@RequestLine("DELETE /files/{fileId}")
	DeletionStatus delete(@Param("fileId") String fileId);

	/**
	 * Returns information about a specific file.
	 *
	 * @return The File object matching the specified ID.
	 */
	@RequestLine("GET /files/{fileId}")
	FileObject retrieve(@Param("fileId") String fileId);

	/**
	 * Returns the contents of the specified file.
	 *
	 * @return The file content.
	 */
	@RequestLine("GET /files/{fileId}/content")
	Response download(@Param("fileId") String fileId);
}
