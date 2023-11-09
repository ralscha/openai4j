package ch.rasc.openai4j.threads;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ThreadUpdateRequest {

	private final Map<String, Object> metadata;

	ThreadUpdateRequest(Map<String, Object> metadata) {
		this.metadata = Map.copyOf(metadata);
	}

	public static ThreadUpdateRequest of(Map<String, Object> metadata) {
		return new ThreadUpdateRequest(metadata);
	}

	@JsonProperty
	public Map<String, Object> metadata() {
		return this.metadata;
	}

}
