package ch.rasc.openai4j.chatcompletions;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemMessage extends Message {
	private final String content;

	SystemMessage(String content) {
		this.content = content;
	}

	/**
	 * Create a new system message with the given content.
	 */
	public static SystemMessage of(String content) {
		return new SystemMessage(content);
	}

	/**
	 * The contents of the system message.
	 */
	@JsonProperty
	public String content() {
		return this.content;
	}

	@Override
	String role() {
		return "system";
	}
}
