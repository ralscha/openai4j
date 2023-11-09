package ch.rasc.openai4j.chatcompletions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tool {
	private final String type;
	private final Function function;

	public Tool(String type, Function function) {
		this.type = type;
		this.function = function;
	}

	public static Tool of(String type, Function function) {
		return new Tool(type, function);
	}

	/**
	 * Tpe type of the tool. Currently, only function is supported.
	 */
	@JsonProperty
	public String type() {
		return this.type;
	}

	@JsonProperty
	public Function function() {
		return this.function;
	}
}
