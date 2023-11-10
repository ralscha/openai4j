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

public class ModelsExample {
	public static void main(String[] args) {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		var models = client.models.list();
		for (var response : models.data()) {
			System.out.println(response);
		}

		System.out.println();

		String model = "dall-e-3";
		var modelDetail = client.models.retrieve(model);
		System.out.println(modelDetail);

		var delete = client.models.delete("test");
		System.out.println(delete);

		System.out.println();

		models = client.models.list();
		for (var response : models.data()) {
			System.out.println(response);
		}
	}

}
