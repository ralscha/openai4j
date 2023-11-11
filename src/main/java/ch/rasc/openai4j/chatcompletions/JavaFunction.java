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
package ch.rasc.openai4j.chatcompletions;

import java.util.function.Function;

import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;

import ch.rasc.openai4j.common.FunctionParameters;

public class JavaFunction<T, R> {

	private final String name;

	private final String description;

	private final Object parameters;

	private final Class<T> parameterClass;

	private final Function<T, R> functionCall;

	private final static SchemaGenerator schemaGenerator;
	static {
		JacksonModule module = new JacksonModule(
				JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
		SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(
				SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON).with(module);
		SchemaGeneratorConfig config = configBuilder.build();
		schemaGenerator = new SchemaGenerator(config);
	}

	private JavaFunction(String name, String description, Class<T> parameterClass,
			Function<T, R> functionCall) {
		this.name = name;
		this.description = description;
		this.parameterClass = parameterClass;
		this.functionCall = functionCall;
		this.parameters = schemaGenerator.generateSchema(parameterClass);
	}

	public static <T, R> JavaFunction<T, R> of(String name, String description,
			Class<T> parameterClass, Function<T, R> functionExecutor) {
		return new JavaFunction<>(name, description, parameterClass, functionExecutor);
	}

	/**
	 * The name of the function to be called. Must be a-z, A-Z, 0-9, or contain
	 * underscores and dashes, with a maximum length of 64.
	 */
	public String name() {
		return this.name;
	}

	/**
	 * A description of what the function does, used by the model to choose when and how
	 * to call the function.
	 */
	public String description() {
		return this.description;
	}

	/*
	 * The parameters the functions accepts, described as a JSON Schema object
	 */
	public Object parameters() {
		return this.parameters;
	}

	public Class<T> parameterClass() {
		return this.parameterClass;
	}

	public ChatCompletionTool toTool() {
		return ChatCompletionTool
				.of(FunctionParameters.of(this.name, this.description, this.parameters));
	}

	public R call(Object parameter) {
		return this.functionCall.apply((T) parameter);
	}

}
