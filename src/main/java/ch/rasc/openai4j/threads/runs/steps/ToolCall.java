package ch.rasc.openai4j.threads.runs.steps;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeIdResolver(ToolCallTypeResolver.class)
public class ToolCall {

	private final String type;

	public ToolCall(String type) {
		this.type = type;
	}

	/**
	 * The type of tool call.
	 */
	public String type() {
		return this.type;
	}
}
