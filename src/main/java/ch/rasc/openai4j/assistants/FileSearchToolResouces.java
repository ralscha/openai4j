package ch.rasc.openai4j.assistants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@SuppressWarnings({ "hiding" })
public class FileSearchToolResouces implements ToolResources {

	@JsonProperty("vector_store_ids")
	private final List<String> vectorStoreIds;

	@JsonProperty("vector_stores")
	private final List<VectorStore> vectorStores;

	private FileSearchToolResouces(Builder builder) {
		this.vectorStoreIds = builder.vectorStoreIds;
		this.vectorStores = builder.vectorStores;
	}

	public List<String> getVectorStoreIds() {
		return this.vectorStoreIds;
	}

	public List<VectorStore> getVectorStores() {
		return this.vectorStores;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private List<String> vectorStoreIds;
		private List<VectorStore> vectorStores;

		/**
		 * The vector store attached to this assistant. There can be a maximum of 1 vector
		 * store attached to the assistant.
		 */
		public Builder vectorStoreIds(String vectorStoreId) {
			this.vectorStoreIds = List.of(vectorStoreId);
			return this;
		}

		/**
		 * A helper to create a vector store with file_ids and attach it to this
		 * assistant. There can be a maximum of 1 vector store attached to the assistant.
		 */
		public Builder vectorStores(VectorStore vectorStore) {
			this.vectorStores = List.of(vectorStore);
			return this;
		}

		/**
		 * A helper to create a vector store with file_ids and attach it to this
		 * assistant. There can be a maximum of 1 vector store attached to the assistant.
		 */
		public Builder vectorStores(
				Function<VectorStore.Builder, VectorStore.Builder> fn) {
			return this.vectorStores(fn.apply(VectorStore.builder()).build());
		}

		public FileSearchToolResouces build() {
			return new FileSearchToolResouces(this);
		}
	}

	@JsonInclude(Include.NON_EMPTY)
	@JsonAutoDetect(fieldVisibility = Visibility.ANY)
	@SuppressWarnings({ "unused" })
	public static class VectorStore {
		@JsonProperty("file_ids")
		private final List<String> fileIds;
		private final Map<String, Object> metadata;

		private VectorStore(Builder builder) {
			this.fileIds = builder.fileIds;
			this.metadata = builder.metadata;
		}

		public static Builder builder() {
			return new Builder();
		}

		public static class Builder {
			private List<String> fileIds;
			private Map<String, Object> metadata;

			/**
			 * A list of file IDs to add to the vector store. There can be a maximum of
			 * 10000 files in a vector store.
			 */
			public Builder fileIds(List<String> fileIds) {
				this.fileIds = new ArrayList<>(fileIds);
				return this;
			}

			/**
			 * A list of file IDs to add to the vector store. There can be a maximum of
			 * 10000 files in a vector store.
			 */
			public Builder addFileIds(String... fileIds) {
				if (fileIds == null || fileIds.length == 0) {
					return this;
				}

				if (this.fileIds == null) {
					this.fileIds = new ArrayList<>();
				}
				this.fileIds.addAll(List.of(fileIds));
				return this;
			}

			/**
			 * Set of 16 key-value pairs that can be attached to a vector store. This can
			 * be useful for storing additional information about the vector store in a
			 * structured format. Keys can be a maximum of 64 characters long and values
			 * can be a maxium of 512 characters long.
			 */
			public Builder metadata(Map<String, Object> metadata) {
				this.metadata = new HashMap<>(metadata);
				return this;
			}

			/**
			 * Add a key-value pair to the metadata
			 */
			public Builder putMetadata(String key, Object value) {
				if (this.metadata == null) {
					this.metadata = new HashMap<>();
				}
				this.metadata.put(key, value);
				return this;
			}

			public VectorStore build() {
				return new VectorStore(this);
			}
		}
	}
}
