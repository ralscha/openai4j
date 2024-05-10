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
package ch.rasc.openai4j.vectorstores.filebatches;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import ch.rasc.openai4j.Beta;
import ch.rasc.openai4j.common.ListResponse;
import ch.rasc.openai4j.vectorstores.files.VectorStoreFile;
import ch.rasc.openai4j.vectorstores.files.VectorStoresFilesListRequest;
import feign.Headers;
import feign.Param;
import feign.QueryMap;
import feign.RequestLine;

@Beta
public interface VectorStoresFileBatchesClient {

	/**
	 * Returns a list of vector store files in a batch.
	 *
	 * @param vectorStoreId The ID of the vector store that the files belong to.
	 * @param batchId The ID of the file batch that the files belong to.
	 *
	 * @return A list of vector store file objects.
	 */
	@RequestLine("GET /vector_stores/{vector_store_id}/file_batches/{batch_id}/files")
	ListResponse<VectorStoreFile> list(@Param("vector_store_id") String vectorStoreId,
			@Param("batch_id") String batchId);

	/**
	 * Returns a list of vector store files in a batch.
	 *
	 * @return A list of vector store file objects.
	 */
	@RequestLine("GET /vector_stores/{vector_store_id}/file_batches/{batch_id}/files")
	ListResponse<VectorStoreFile> list(@Param("vector_store_id") String vectorStoreId,
			@Param("batch_id") String batchId,
			@QueryMap Map<String, Object> queryParameters);

	/**
	 * Returns a list of vector store files in a batch.
	 *
	 * @param request A list request object with configuration for paging and ordering
	 * @return A list of vector store file objects.
	 */
	default ListResponse<VectorStoreFile> list(String vectorStoreId, String batchId,
			VectorStoresFilesListRequest request) {
		return this.list(vectorStoreId, batchId, request.toMap());
	}

	/**
	 * Returns a list of vector store files in a batch.
	 *
	 * @param fn A list request object with configuration for paging and ordering
	 * @return A list of vector store file objects.
	 */
	default ListResponse<VectorStoreFile> list(String vectorStoreId, String batchId,
			Function<VectorStoresFilesListRequest.Builder, VectorStoresFilesListRequest.Builder> fn) {
		return this.list(vectorStoreId, batchId,
				fn.apply(VectorStoresFilesListRequest.builder()).build());
	}

	/**
	 * Create a vector store file batch.
	 *
	 * @return A vector store file batch object.
	 */
	@RequestLine("POST /vector_stores/{vector_store_id}/file_batches")
	@Headers("Content-Type: application/json")
	VectorStoreFileBatch create(@Param("vector_store_id") String vectorStoreId,
			VectorStoreFileBatchCreateRequest request);

	/**
	 * Create a vector store file batch.
	 *
	 * @return A vector store file batch object.
	 */
	default VectorStoreFileBatch create(String vectorStoreId, List<String> fileIds) {
		return this.create(vectorStoreId, VectorStoreFileBatchCreateRequest.of(fileIds));
	}

	/**
	 * Create a vector store file batch.
	 *
	 * @return A vector store file batch object.
	 */
	default VectorStoreFileBatch create(String vectorStoreId, String... fileIds) {
		return this.create(vectorStoreId,
				VectorStoreFileBatchCreateRequest.of(List.of(fileIds)));
	}

	/**
	 * Retrieves a vector store file batch.
	 * 
	 * @param vectorStoreId The ID of the vector store that the file batch belongs to.
	 * @param batchId The ID of the file batch being retrieved.
	 * 
	 * @return The vector store file batch object.
	 */
	@RequestLine("GET /vector_stores/{vector_store_id}/file_batches/{batch_id}")
	VectorStoreFileBatch retrieve(@Param("vector_store_id") String vectorStoreId,
			@Param("batch_id") String batchId);

	/**
	 * Cancel a vector store file batch. This attempts to cancel the processing of files
	 * in this batch as soon as possible.
	 * 
	 * @param vectorStoreId The ID of the vector store that the file batch belongs to.
	 * @param batchId The ID of the file batch to cancel.
	 * 
	 * @return The modified vector store file batch object.
	 */
	@RequestLine("POST /vector_stores/{vector_store_id}/file_batches/{batch_id}/cancel")
	@Headers("Content-Type: application/json")
	VectorStoreFileBatch cancel(@Param("vector_store_id") String vectorStoreId,
			@Param("batch_id") String batchId);

}
