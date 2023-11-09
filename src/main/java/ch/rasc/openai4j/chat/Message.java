package ch.rasc.openai4j.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Message {
	/**
	 * The role of the messages author
	 */
	@JsonProperty
	abstract String role();
}
