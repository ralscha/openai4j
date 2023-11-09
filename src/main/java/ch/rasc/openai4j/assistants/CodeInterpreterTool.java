package ch.rasc.openai4j.assistants;

public class CodeInterpreterTool implements Tool {

	@Override
	public String type() {
		return "code_interpreter";
	}

	public static CodeInterpreterTool of() {
		return new CodeInterpreterTool();
	}

}
