package ch.rasc.openai4j.assistants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import ch.rasc.openai4j.common.Function;

public class FunctionTool implements Tool {

	private final Function function;

	private FunctionTool(Function function) {
		this.function = function;
	}

	@Override
	public String type() {
		return "function";
	}

	@JsonProperty
	Function function() {
		return this.function;
	}

	@JsonCreator
	public static FunctionTool of(@JsonProperty("function") Function function) {
		return new FunctionTool(function);
	}
}
