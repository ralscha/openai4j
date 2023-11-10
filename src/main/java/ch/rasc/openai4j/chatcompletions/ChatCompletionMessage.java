package ch.rasc.openai4j.chatcompletions;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ChatCompletionMessage {
	/**
	 * The role of the messages author
	 */
	@JsonProperty
	abstract String role();
}
