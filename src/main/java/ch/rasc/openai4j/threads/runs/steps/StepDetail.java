package ch.rasc.openai4j.threads.runs.steps;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(use = Id.NAME, property = "type")
@JsonTypeIdResolver(StepDetailTypeResolver.class)
public class StepDetail {
	private final String type;

	public StepDetail(String type) {
		this.type = type;
	}

	/**
	 * The type of step detail.
	 */
	public String type() {
		return this.type;
	}
}
