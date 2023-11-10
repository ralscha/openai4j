package ch.rasc.openai4j.chatcompletions;

import com.fasterxml.jackson.annotation.JsonProperty;

import ch.rasc.openai4j.common.Function;

public class ChatCompletionTool {
	private final String type;
	private final Function function;

	public ChatCompletionTool(String type, Function function) {
		this.type = type;
		this.function = function;
	}

	public static ChatCompletionTool of(String type, Function function) {
		return new ChatCompletionTool(type, function);
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
