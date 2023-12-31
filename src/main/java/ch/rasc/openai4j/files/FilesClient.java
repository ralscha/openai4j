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

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import ch.rasc.openai4j.common.DeletionStatus;
import ch.rasc.openai4j.common.ListResponse;
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
	ListResponse<FileObject> list();

	/**
	 * Returns a list of files that belong to the user's organization with the given
	 * purpose.
	 *
	 * @return A list of File objects.
	 */
	@RequestLine("GET /files?purpose={purpose}")
	ListResponse<FileObject> list(@Param("purpose") String purpose);

	/**
	 * Returns a list of files that belong to the user's organization with the given
	 * purpose.
	 *
	 * @return A list of File objects.
	 */
	default ListResponse<FileObject> list(Purpose purpose) {
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
	default FileObject create(Path file, Purpose purpose) {
		return this.create(file.toFile(), purpose.value());
	}

	/**
	 * Upload a file for assistants. The size of all the files uploaded by one
	 * organization can be up to 100 GB. The size of individual files for can be a maximum
	 * of 512MB.
	 *
	 * @return The uploaded File object.
	 */
	default FileObject createForAssistants(Path file) {
		return this.create(file, Purpose.ASSISTANTS);
	}

	/**
	 * Upload a file for fine-tune. The size of all the files uploaded by one organization
	 * can be up to 100 GB. The size of individual files for can be a maximum of 512MB.
	 * The Fine-tuning API only supports .jsonl files.
	 *
	 * @return The uploaded File object.
	 */
	default FileObject createForFineTune(Path file) {
		return this.create(file, Purpose.FINE_TUNE);
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
	@RequestLine("DELETE /files/{file_id}")
	DeletionStatus delete(@Param("file_id") String fileId);

	/**
	 * Returns information about a specific file.
	 *
	 * @return The File object matching the specified ID.
	 */
	@RequestLine("GET /files/{file_id}")
	FileObject retrieve(@Param("file_id") String fileId);

	/**
	 * Returns the contents of the specified file.
	 *
	 * @return The file content.
	 */
	@RequestLine("GET /files/{file_id}/content")
	Response retrieveContent(@Param("file_id") String fileId);

	default FileObject waitForProcessing(String fileId) {
		return this.waitForProcessing(fileId, 5, TimeUnit.SECONDS, 30, TimeUnit.SECONDS);
	}

	default FileObject waitForProcessing(String fileId, long pollInterval,
			TimeUnit pollIntervalTimeUnit, long maxWait, TimeUnit maxWaitTimeUnit) {
		long start = System.currentTimeMillis();
		FileObject file = this.retrieve(fileId);
		while (!file.status().isTerminal()) {
			try {
				pollIntervalTimeUnit.sleep(pollInterval);
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new RuntimeException(e);
			}

			if (System.currentTimeMillis() - start > maxWaitTimeUnit.toMillis(maxWait)) {
				throw new RuntimeException("Giving up on waiting for file " + fileId
						+ " to finish processing after " + maxWait + " "
						+ maxWaitTimeUnit);
			}

			file = this.retrieve(fileId);
		}

		return file;
	}

}
