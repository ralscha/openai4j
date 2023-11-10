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

import java.nio.file.Path;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public interface FileUploadRequest {
	/**
	 * The File object to be uploaded.
	 */
	Path file();

	/**
	 * The intended purpose of the uploaded file.
	 *
	 * Use "fine-tune" for Fine-tuning and "assistants" for Assistants and Messages. This
	 * allows us to validate the format of the uploaded file is correct for fine-tuning.
	 */
	Purpose purpose();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableFileUploadRequest.Builder {
	}
}
