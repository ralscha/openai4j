package ch.rasc.openai4j.threads.runs;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThreadRunUpdateRequest {
	private final Map<String, Object> metadata;

	ThreadRunUpdateRequest(Map<String, Object> metadata) {
		this.metadata = Map.copyOf(metadata);
	}

	public static ThreadRunUpdateRequest of(Map<String, Object> metadata) {
		return new ThreadRunUpdateRequest(metadata);
	}

	@JsonProperty
	public Map<String, Object> metadata() {
		return this.metadata;
	}
}
