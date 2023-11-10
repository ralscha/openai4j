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

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Util {
	public static String getApiKey() {
		String token = System.getenv("OPENAI_API_KEY");
		if (token == null || token.isBlank()) {
			// read from .env in current directory
			Properties props = new Properties();
			try (InputStream is = Files.newInputStream(Paths.get(".env"))) {
				props.load(is);
				token = props.getProperty("OPENAI_API_KEY");
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		return token;
	}
}
