package ch.rasc.openai4j.assistants;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(use = Id.NAME, include = As.PROPERTY, property = "type")
@JsonTypeIdResolver(ToolTypeResolver.class)
public interface Tool {
	String type();
}
