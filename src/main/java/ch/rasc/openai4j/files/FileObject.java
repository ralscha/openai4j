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

import ch.rasc.openai4j.Nullable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public record FileObject(String id, Integer bytes,
		@JsonProperty("created_at") Integer createdAt, String filename, String object,
		Purpose purpose, Status status,
		@JsonProperty("status_details") @Nullable String statusDetails) {

	public enum Status {
		UPLOADED("uploaded"), PROCESSED("processed"), ERROR("error"), DELETED("deleted");

		private final String value;

		Status(String value) {
			this.value = value;
		}

		@JsonValue
		public String value() {
			return this.value;
		}

		public boolean isTerminal() {
			return this == PROCESSED || this == ERROR || this == DELETED;
		}
	}

}
