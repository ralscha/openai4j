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
package ch.rasc.openai4j.assistants.files;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class AssistantFileCreateRequest {

	@JsonProperty("file_id")
	private final String fileId;

	private AssistantFileCreateRequest(Builder builder) {
		this.fileId = builder.fileId;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {
		private String fileId;

		private Builder() {
		}

		/**
		 * A File ID (with purpose="assistants") that the assistant should use. Useful for
		 * tools like retrieval and code_interpreter that can access files.
		 */
		public Builder fileId(String val) {
			this.fileId = val;
			return this;
		}

		public AssistantFileCreateRequest build() {
			return new AssistantFileCreateRequest(this);
		}
	}
}
