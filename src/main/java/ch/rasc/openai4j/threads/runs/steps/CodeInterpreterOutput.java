package ch.rasc.openai4j.threads.runs.steps;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeIdResolver(CodeInterpreterOutputTypeResolver.class)
public class CodeInterpreterOutput {
	private final String type;

	public CodeInterpreterOutput(String type) {
		this.type = type;
	}

	/**
	 * The type of step detail.
	 */
	public String type() {
		return this.type;
	}
}
