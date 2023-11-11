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
package ch.rasc.openai4j;

import java.io.IOException;
import java.io.Serial;

public class OpenAIApiException extends IOException {
	@Serial
	private static final long serialVersionUID = 1L;

	private final String message;
	private final String type;
	private final String param;
	private final String code;

	public OpenAIApiException(String message, String type, String param, String code) {
		super(type + ": " + message);
		this.message = message;
		this.type = type;
		this.param = param;
		this.code = code;
	}

	public String message() {
		return this.message;
	}

	public String type() {
		return this.type;
	}

	public String param() {
		return this.param;
	}

	public String code() {
		return this.code;
	}

}
