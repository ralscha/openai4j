package ch.rasc.openai4j.threads.runs.steps;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ToolCallRetrieval extends ToolCall {

	private final String id;

	private final Map<String, Object> retrieval;

	@JsonCreator
	public ToolCallRetrieval(@JsonProperty("type") String type,
			@JsonProperty("id") String id,
			@JsonProperty("retrieval") Map<String, Object> retrieval) {
		super(type);
		this.id = id;
		this.retrieval = retrieval;
	}

	/**
	 * The ID of the tool call object.
	 */
	public String id() {
		return this.id;
	}

	/**
	 * For now, this is always going to be an empty object.
	 */
	public Map<String, Object> retrieval() {
		return this.retrieval;
	}

}
