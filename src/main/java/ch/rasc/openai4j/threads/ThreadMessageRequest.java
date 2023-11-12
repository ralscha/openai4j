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
package ch.rasc.openai4j.threads;

import java.util.List;
import java.util.Map;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonSerialize(as = ImmutableThreadMessageRequest.class)
@JsonInclude(Include.NON_EMPTY)
public interface ThreadMessageRequest {
	static Builder builder() {
		return new Builder();
	}

	/**
	 * The role of the entity that is creating the message. Currently only user is
	 * supported.
	 */
	@Value.Default
	default String role() {
		return "user";
	}

	/**
	 * The content of the message.
	 */
	String content();

	/**
	 * A list of File IDs that the message should use. There can be a maximum of 10 files
	 * attached to a message. Useful for tools like retrieval and code_interpreter that
	 * can access and use files.
	 */
	@Nullable
	@JsonProperty("file_ids")
	List<String> fileIds();

	/**
	 * Set of 16 key-value pairs that can be attached to an object. This can be useful for
	 * storing additional information about the object in a structured format. Keys can be
	 * a maximum of 64 characters long and values can be a maxium of 512 characters long.
	 */
	@Nullable
	Map<String, Object> metadata();

	final class Builder extends ImmutableThreadMessageRequest.Builder {
	}
}
