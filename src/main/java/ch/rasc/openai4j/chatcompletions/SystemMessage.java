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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SystemMessage extends ChatCompletionMessage {
	private final String content;

	SystemMessage(String content) {
		this.content = content;
	}

	/**
	 * Create a new system message with the given content.
	 */
	public static SystemMessage of(String content) {
		return new SystemMessage(content);
	}

	/**
	 * The contents of the system message.
	 */
	@JsonInclude
	@JsonProperty
	public String content() {
		return this.content;
	}

	@Override
	String role() {
		return "system";
	}
}
