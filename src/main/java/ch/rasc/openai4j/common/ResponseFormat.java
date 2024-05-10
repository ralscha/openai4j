package ch.rasc.openai4j.common;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonValue;

public class ResponseFormat {
	private final Object value;

	ResponseFormat(Object value) {
		this.value = value;
	}

	/**
	 * If text the model can return text or any value needed.
	 */
	public static ResponseFormat text() {
		return new ResponseFormat(Map.of("type", "text"));
	}

	/**
	 * Setting to { "type": "json_object" } enables JSON mode, which guarantees the
	 * message the model generates is valid JSON.
	 * <p>
	 * Important: when using JSON mode, you must also instruct the model to produce JSON
	 * yourself via a system or user message. Without this, the model may generate an
	 * unending stream of whitespace until the generation reaches the token limit,
	 * resulting in a long-running and seemingly "stuck" request. Also note that the
	 * message content may be partially cut off if finish_reason="length", which indicates
	 * the generation exceeded max_tokens or the conversation exceeded the max context
	 * length.
	 */
	public static ResponseFormat jsonObject() {
		return new ResponseFormat(Map.of("type", "json_object"));
	}

	/**
	 * auto is the default value
	 */
	public static ResponseFormat auto() {
		return new ResponseFormat("auto");
	}

	@JsonValue
	public Object value() {
		return this.value;
	}
}