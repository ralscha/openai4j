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

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.rasc.openai4j.threads.Thread;
import ch.rasc.openai4j.threads.ThreadCreateRequest;
import ch.rasc.openai4j.threads.ThreadMessage;
import ch.rasc.openai4j.threads.ThreadUpdateRequest;

public class ThreadsExample {
	public static void main(String[] args) throws JsonProcessingException {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		ThreadCreateRequest request = ThreadCreateRequest.builder()
				.addMessages(ThreadMessage.builder().content("hello").build())
				.putMetadata("name", "ralph").build();
		ObjectMapper om = new ObjectMapper();
		System.out.println(om.writeValueAsString(request));
		Thread response = client.threads.create(request);
		System.out.println(response);

		var r = client.threads.retrieve(response.id());
		System.out.println(r);

		var ru = client.threads.update(response.id(),
				ThreadUpdateRequest.of(Map.of("name", "john")));
		System.out.println(ru);

		r = client.threads.retrieve(response.id());
		System.out.println(r);

		var df = client.threads.delete(response.id());
		System.out.println(df);
	}

}
