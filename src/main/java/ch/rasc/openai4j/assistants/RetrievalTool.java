package ch.rasc.openai4j.assistants;

public class RetrievalTool implements Tool {

	@Override
	public String type() {
		return "retrieval";
	}

	public static RetrievalTool of() {
		return new RetrievalTool();
	}

}
