package ch.rasc.openai4j.threads.runs.steps;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ToolCallCodeInterpreter extends ToolCall {

	private final String id;

	private final CodeInterpreter codeInterpreter;

	@JsonCreator
	public ToolCallCodeInterpreter(@JsonProperty("type") String type,
			@JsonProperty("id") String id,
			@JsonProperty("code_interpreter") CodeInterpreter codeInterpreter) {
		super(type);
		this.id = id;
		this.codeInterpreter = codeInterpreter;
	}

	/**
	 * The ID of the tool call.
	 */
	public String id() {
		return this.id;
	}

	/**
	 * The Code Interpreter tool call definition.
	 */
	public CodeInterpreter codeInterpreter() {
		return this.codeInterpreter;
	}

	class CodeInterpreter {
		private final String input;

		private final CodeInterpreterOutput[] outputs;

		public CodeInterpreter(@JsonProperty("input") String input,
				@JsonProperty("outputs") CodeInterpreterOutput[] outputs) {
			this.input = input;
			this.outputs = outputs;
		}

		/**
		 * The input to the Code Interpreter tool call.
		 */
		public String input() {
			return this.input;
		}

		/**
		 * The outputs from the Code Interpreter tool call.
		 */
		public CodeInterpreterOutput[] outputs() {
			return this.outputs;
		}

	}

}
