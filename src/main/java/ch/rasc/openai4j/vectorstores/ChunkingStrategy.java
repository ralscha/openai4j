package ch.rasc.openai4j.vectorstores;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import ch.rasc.openai4j.vectorstores.ChunkingStrategy.AutoChunkingStrategy;
import ch.rasc.openai4j.vectorstores.ChunkingStrategy.OtherChunkingStrategy;
import ch.rasc.openai4j.vectorstores.ChunkingStrategy.StaticChunkingStrategy;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({ @Type(value = AutoChunkingStrategy.class, name = "auto"),
		@Type(value = StaticChunkingStrategy.class, name = "static"),
		@Type(value = OtherChunkingStrategy.class, name = "other") })
public interface ChunkingStrategy {
	String type();

	public static class AutoChunkingStrategy implements ChunkingStrategy {
		@Override
		@JsonProperty("type")
		public String type() {
			return "auto";
		}
	}

	public static class StaticChunkingStrategy implements ChunkingStrategy {
		@JsonProperty("static")
		private final Map<String, Integer> staticChunking = new HashMap<>();

		@Override
		@JsonProperty("type")
		public String type() {
			return "static";
		}

		/**
		 * @param maxChunkSizeTokens The maximum number of tokens in each chunk. The
		 * default value is 800. The minimum value is 100 and the maximum value is 4096.
		 * @param chunkOverlapTokens The number of tokens that overlap between chunks. The
		 * default value is 400. Note that the overlap must not exceed half of
		 * max_chunk_size_tokens.
		 */
		@JsonCreator
		StaticChunkingStrategy(
				@JsonProperty("static.max_chunk_size_tokens") int maxChunkSizeTokens,
				@JsonProperty("static.chunk_overlap_tokens") int chunkOverlapTokens) {
			this.staticChunking.put("max_chunk_size_tokens", maxChunkSizeTokens);
			this.staticChunking.put("chunk_overlap_tokens", chunkOverlapTokens);
		}

		/**
		 * @param maxChunkSizeTokens The maximum number of tokens in each chunk. The
		 * default value is 800. The minimum value is 100 and the maximum value is 4096.
		 * @param chunkOverlapTokens The number of tokens that overlap between chunks. The
		 * default value is 400. Note that the overlap must not exceed half of
		 * max_chunk_size_tokens.
		 */
		public static StaticChunkingStrategy of(int maxChunkSizeTokens,
				int chunkOverlapTokens) {
			return new StaticChunkingStrategy(maxChunkSizeTokens, chunkOverlapTokens);
		}

		public int maxChunkSizeTokens() {
			return this.staticChunking.get("max_chunk_size_tokens");
		}

		public int chunkOverlapTokens() {
			return this.staticChunking.get("chunk_overlap_tokens");
		}

	}

	/**
	 * This is returned when the chunking strategy is unknown. Typically, this is because
	 * the file was indexed before the chunking_strategy concept was introduced in the
	 * API.
	 */
	public static class OtherChunkingStrategy implements ChunkingStrategy {
		@Override
		@JsonProperty("type")
		public String type() {
			return "other";
		}
	}

}