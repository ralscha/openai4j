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
package ch.rasc.openai4j.threads.messages.files;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * A list of files attached to a message.
 */
@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableThreadMessageFile.class)
public interface ThreadMessageFile {

	/*
	 * The identifier, which can be referenced in API endpoints.
	 */
	String id();

	/*
	 * The object type, which is always thread.message.file.
	 */
	String object();

	/*
	 * The Unix timestamp (in seconds) for when the message file was created.
	 */
	@JsonProperty("created_at")
	int createdAt();

	/*
	 * The ID of the message that the File is attached to.
	 */
	@JsonProperty("message_id")
	String messageId();
}
