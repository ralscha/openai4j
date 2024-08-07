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
package ch.rasc.openai4j.files;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Purpose {
	FINE_TUNE("fine-tune"), FINE_TUNE_RESULTS("fine-tune-results"),
	ASSISTANTS("assistants"), ASSISTANTS_OUTPUT("assistants_output"), BATCH("batch"),
	BATCH_OUTPUT("batch_output"), VISION("vision");

	private final String value;

	Purpose(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return this.value;
	}
}
