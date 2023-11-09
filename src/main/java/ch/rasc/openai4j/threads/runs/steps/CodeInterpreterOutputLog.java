package ch.rasc.openai4j.threads.runs.steps;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CodeInterpreterOutputLog extends CodeInterpreterOutput {
	private final String logs;

	@JsonCreator
	public CodeInterpreterOutputLog(@JsonProperty("type") String type,
			@JsonProperty("logs") String logs) {
		super(type);
		this.logs = logs;
	}

	/**
	 * The text output from the Code Interpreter tool call.
	 */
	public String logs() {
		return this.logs;
	}
}
