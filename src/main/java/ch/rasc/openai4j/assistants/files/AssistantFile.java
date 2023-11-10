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

import org.immutables.value.Value;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * A list of Files attached to an assistant.
 */
@Value.Immutable(builder = false)
@Value.Style(visibility = Value.Style.ImplementationVisibility.PACKAGE,
		allParameters = true)
@JsonDeserialize(as = ImmutableAssistantFile.class)
public interface AssistantFile {

	/*
	 * The identifier, which can be referenced in API endpoints.
	 */
	String id();

	/*
	 * The object type, which is always assistant.file.
	 */
	String object();

	/*
	 * The Unix timestamp (in seconds) for when the assistant file was created.
	 */
	@JsonProperty("created_at")
	int createdAt();

	/*
	 * The assistant ID that the file is attached to.
	 */
	@JsonProperty("assistant_id")
	String assistantId();
}
