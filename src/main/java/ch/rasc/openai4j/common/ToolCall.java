package ch.rasc.openai4j.common;

public record ToolCall(String id, String type, FunctionArguments function) {
	/**
	 * The ID of the tool call. This ID must be referenced when you submit the tool
	 * outputs in using the Submit tool outputs to run endpoint.
	 */
	@Override
	public String id() {
		return this.id;
	}

	/**
	 *
	 * The type of the tool. Currently, only function is supported.
	 */
	@Override
	public String type() {
		return this.type;
	}

	/**
	 * The function that the model called.
	 */
	@Override
	public FunctionArguments function() {
		return this.function;
	}
}