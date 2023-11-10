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
package ch.rasc.openai4j.assistants;

import java.util.Map;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.Nullable;

/**
 * Represents an assistant that can call the model and use tools.
 */
@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableAssistant.class)
public interface Assistant {

	/**
	 * The identifier, which can be referenced in API endpoints.
	 */
	String id();

	/**
	 * The object type, which is always assistant.
	 */
	String object();

	/**
	 * The Unix timestamp (in seconds) for when the assistant was created.
	 */
	@JsonProperty("created_at")
	int createdAt();

	/**
	 * The name of the assistant. The maximum length is 256 characters.
	 */
	@Nullable
	String name();

	/**
	 * The description of the assistant. The maximum length is 512 characters.
	 */
	@Nullable
	String description();

	/**
	 * ID of the model to use. You can use the List models API to see all of your
	 * available models, or see our Model overview for descriptions of them.
	 */
	String model();

	/**
	 * The system instructions that the assistant uses. The maximum length is 32768
	 * characters.
	 */
	@Nullable
	String instructions();

	Tool[] tools();

	/**
	 * A list of file IDs attached to this assistant. There can be a maximum of 20 files
	 * attached to the assistant. Files are ordered by their creation date in ascending
	 * order.
	 */
	@JsonProperty("file_ids")
	String[] fileIds();

	/**
	 * Set of 16 key-value pairs that can be attached to an object. This can be useful for
	 * storing additional information about the object in a structured format. Keys can be
	 * a maximum of 64 characters long and values can be a maxium of 512 characters long.
	 */
	Map<String, Object> metadata();

}
