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
package ch.rasc.openai4j.common;

import ch.rasc.openai4j.Nullable;

/**
 * @param code A machine-readable error code.
 * @param message A human-readable description of the error.
 * @param param For fine-tuning. The parameter that was invalid, usually training_file or
 * validation_file. This field will be null if the failure was not parameter-specific.
 */
public record Error(String code, String message, @Nullable String param) {
}
