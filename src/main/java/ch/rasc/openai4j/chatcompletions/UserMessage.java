package ch.rasc.openai4j.chatcompletions;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserMessage extends Message {
	private final Object content;

	UserMessage(Object content) {
		this.content = content;
	}

	/**
	 * Create a new user message with the given content.
	 */
	public static UserMessage of(String content) {
		return new UserMessage(content);
	}

	public static UserMessage of(List<String> content) {
		return new UserMessage(List.copyOf(content));
	}

	/**
	 * The contents of the user message.
	 */
	@JsonProperty
	public Object content() {
		return this.content;
	}

	@Override
	String role() {
		return "user";
	}

}
