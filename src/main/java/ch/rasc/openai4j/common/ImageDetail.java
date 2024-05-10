package ch.rasc.openai4j.common;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ImageDetail {
	LOW("low"), HIGH("high"), AUTO("auto");

	private final String value;

	ImageDetail(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return this.value;
	}
}