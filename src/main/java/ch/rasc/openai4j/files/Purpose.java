package ch.rasc.openai4j.files;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Purpose {
	FINE_TUNE("fine-tune"), FINE_TUNE_RESULTS("fine-tune-results"),
	ASSISTANTS("assistants"), ASSISTANTS_OUTPUT("assistants_output");

	private final String value;

	Purpose(String value) {
		this.value = value;
	}

	@JsonValue
	public String toValue() {
		return this.value;
	}
}
