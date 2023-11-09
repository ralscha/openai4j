package ch.rasc.openai4j.threads.runs.steps;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StepDetailToolCall extends StepDetail {

	private final ToolCall[] toolCalls;

	@JsonCreator
	public StepDetailToolCall(@JsonProperty("type") String type,
			@JsonProperty("tool_calls") ToolCall[] toolCalls) {
		super(type);
		this.toolCalls = toolCalls;
	}

	/**
	 * An array of tool calls the run step was involved in.
	 */
	public ToolCall[] toolCalls() {
		return this.toolCalls;
	}

}
