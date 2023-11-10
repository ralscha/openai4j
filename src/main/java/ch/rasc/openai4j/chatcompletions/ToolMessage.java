package ch.rasc.openai4j.chatcompletions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToolMessage extends ChatCompletionMessage {

	private final String content;
	private final String toolCallId;

	ToolMessage(String content, String toolCallId) {
		this.content = content;
		this.toolCallId = toolCallId;
	}

	/**
	 * Create a new tool message.
	 * @param content The contents of the tool message.
	 * @param toolCallId Tool call that this message is responding to.
	 * @return A new tool message.
	 */
	public static ToolMessage of(String content, String toolCallId) {
		return new ToolMessage(content, toolCallId);
	}

	/**
	 * The contents of the tool message.
	 */
	@JsonProperty
	public String content() {
		return this.content;
	}

	/**
	 * Tool call that this message is responding to.
	 */
	@JsonProperty("tool_call_id")
	public String toolCallId() {
		return this.toolCallId;
	}

	@Override
	String role() {
		return "tool";
	}
}
