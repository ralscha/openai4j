package ch.rasc.openai4j.threads.messages;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThreadMessageUpdateRequest {
	private final Map<String, Object> metadata;

	ThreadMessageUpdateRequest(Map<String, Object> metadata) {
		this.metadata = Map.copyOf(metadata);
	}

	public static ThreadMessageUpdateRequest of(Map<String, Object> metadata) {
		return new ThreadMessageUpdateRequest(metadata);
	}

	@JsonProperty
	public Map<String, Object> metadata() {
		return this.metadata;
	}
}
