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
import ch.rasc.openai4j.assistants.Tool;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE, depluralize = true)
@JsonSerialize(as = ImmutableThreadCreateRunCreateRequest.class)
@JsonInclude(Include.NON_EMPTY)
@Value.Enclosing
public interface ThreadCreateRunCreateRequest {

	static Builder builder() {
		return new Builder();
	}

	/*
	 * The ID of the assistant to use to execute this run.
	 */
	@JsonProperty("assistant_id")
	String assistantId();

	@Nullable
	Thread thread();

	/*
	 * The ID of the Model to be used to execute this run. If a value is provided here, it
	 * will override the model associated with the assistant. If not, the model associated
	 * with the assistant will be used.
	 */
	@Nullable
	String model();

	/*
	 * Override the default system message of the assistant. This is useful for modifying
	 * the behavior on a per-run basis.
	 */
	@Nullable
	String instructions();

	/*
	 * Override the tools the assistant can use for this run. This is useful for modifying
	 * the behavior on a per-run basis.
	 */
	@Nullable
	List<Tool> tools();

	/*
	 * Set of 16 key-value pairs that can be attached to an object. This can be useful for
	 * storing additional information about the object in a structured format. Keys can be
	 * a maximum of 64 characters long and values can be a maxium of 512 characters long.
	 */
	@Nullable
	Map<String, Object> metadata();

	record Thread(List<ThreadMessageRequest> messages, Map<String, Object> metadata) {

		/**
		 * A message to start the thread with.
		 */
		public static Thread of(ThreadMessageRequest message) {
			return new Thread(List.of(message), null);
		}

		/**
		 * A message to start the thread with.
		 */
		public static Thread of(ThreadMessageRequest message,
				Map<String, Object> metadata) {
			return new Thread(List.of(message), metadata);
		}

		/**
		 * A list of messages to start the thread with.
		 */
		public static Thread of(List<ThreadMessageRequest> messages) {
			return new Thread(messages, null);
		}

		/**
		 * A list of messages to start the thread with.
		 */
		public static Thread of(List<ThreadMessageRequest> messages,
				Map<String, Object> metadata) {
			return new Thread(messages, metadata);
		}

	}

	final class Builder extends ImmutableThreadCreateRunCreateRequest.Builder {
	}

}
