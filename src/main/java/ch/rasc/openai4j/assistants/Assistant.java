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

import com.fasterxml.jackson.annotation.JsonProperty;

import ch.rasc.openai4j.Nullable;

/**
 * Represents an assistant that can call the model and use tools.
 */
public record Assistant(String id, String object,
		@JsonProperty("created_at") int createdAt, @Nullable String name,
		@Nullable String description, String model, @Nullable String instructions,
		Tool[] tools, @JsonProperty("file_ids") String[] fileIds,
		Map<String, Object> metadata) {
}
