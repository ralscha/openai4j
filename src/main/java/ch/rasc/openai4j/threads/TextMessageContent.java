package ch.rasc.openai4j.threads;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TextMessageContent extends MessageContent {
	private final String text;

	private TextMessageContent(String text) {
		super("text");
		this.text = text;
	}

	public static TextMessageContent of(String text) {
		return new TextMessageContent(text);
	}

	@JsonProperty("text")
	public String getText() {
		return this.text;
	}
}