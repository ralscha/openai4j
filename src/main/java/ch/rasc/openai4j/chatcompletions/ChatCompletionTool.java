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

import com.fasterxml.jackson.annotation.JsonProperty;

import ch.rasc.openai4j.common.Function;

public class ChatCompletionTool {
	private final String type;
	private final Function function;

	public ChatCompletionTool(String type, Function function) {
		this.type = type;
		this.function = function;
	}

	public static ChatCompletionTool of(String type, Function function) {
		return new ChatCompletionTool(type, function);
	}

	/**
	 * Tpe type of the tool. Currently, only function is supported.
	 */
	@JsonProperty
	public String type() {
		return this.type;
	}

	@JsonProperty
	public Function function() {
		return this.function;
	}
}
