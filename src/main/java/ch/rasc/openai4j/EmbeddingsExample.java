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

import java.util.Arrays;

import ch.rasc.openai4j.embeddings.EmbeddingCreateRequest;
import ch.rasc.openai4j.embeddings.EmbeddingCreateRequest.EncodingFormat;

public class EmbeddingsExample {
	public static void main(String[] args) {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(c -> c.apiKey(apiKey));

		EmbeddingCreateRequest request = EmbeddingCreateRequest.builder()
				.addInput("HelloWorld").addInput("HelloWorld2")
				.encodingFormat(EncodingFormat.BASE64).model("text-embedding-ada-002")
				.build();
		var response = client.embeddings.create(request);
		System.out.println(response.data().get(0).embedding().base64());

		response = client.embeddings.create(r -> r.addInput("HelloWorld")
				.addInput("HelloWorld2").encodingFormat(EncodingFormat.FLOAT)
				.model("text-embedding-ada-002"));
		double[] embeddings = response.data().get(0).embedding().doubleArray();
		System.out.println(Arrays.toString(embeddings));
	}

}
