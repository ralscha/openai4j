/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.rasc.openai4j.common;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Function {
	private final String description;
	private final String name;
	private final Object parameters;

	private Function(String description, String name, Object parameters) {
		this.description = description;
		this.name = name;
		this.parameters = parameters;
	}

	@JsonCreator
	public static Function of(@JsonProperty("description") String description,
			@JsonProperty("name") String name,
			@JsonProperty("parameters") Object parameters) {
		return new Function(description, name, parameters);
	}

	public static Function of(String name, Object parameters) {
		return new Function(null, name, parameters);
	}

	/**
	 * A description of what the function does, used by the model to choose when and how
	 * to call the function.
	 */
	@JsonProperty
	public String description() {
		return this.description;
	}

	/**
	 * The name of the function to be called. Must be a-z, A-Z, 0-9, or contain
	 * underscores and dashes, with a maximum length of 64.
	 */
	@JsonProperty
	public String name() {
		return this.name;
	}

	/**
	 * The parameters the functions accepts, described as a JSON Schema object.
	 * <p>
	 * To describe a function that accepts no parameters, provide the value {"type":
	 * "object", "properties": {}}.
	 */
	public static Map<String, Object> NO_PARAMETERS = Map.of("type", "object",
			"properties", Map.of());

	@JsonProperty
	public Object parameters() {
		return this.parameters;
	}

}
