package ch.rasc.openai4j.threads.runs.steps;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ToolCallFunction extends ToolCall {

	private final String id;

	private final Function function;

	@JsonCreator
	public ToolCallFunction(@JsonProperty("type") String type,
			@JsonProperty("id") String id, @JsonProperty("function") Function function) {
		super(type);
		this.id = id;
		this.function = function;
	}

	/**
	 * The ID of the tool call object.
	 */
	public String id() {
		return this.id;
	}

	/**
	 * The definition of the function that was called.
	 */
	public Function function() {
		return this.function;
	}

	class Function {
		private final String name;
		private final String arguments;
		private final String output;

		@JsonCreator
		public Function(@JsonProperty("name") String name,
				@JsonProperty("arguments") String arguments,
				@JsonProperty("output") String output) {
			this.name = name;
			this.arguments = arguments;
			this.output = output;
		}

		/*
		 * The name of the function.
		 */
		public String name() {
			return this.name;
		}

		/*
		 * The arguments passed to the function.
		 */
		public String arguments() {
			return this.arguments;
		}

		/*
		 * The output of the function. This will be null if the outputs have not been
		 * submitted yet.
		 */
		public String output() {
			return this.output;
		}
	}

}
