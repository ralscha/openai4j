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

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import ch.rasc.openai4j.audio.AudioSpeechRequest;
import feign.Response;

public class AudioExample {

	public static void main(String[] args) {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		var input = "Hallo schöne Welt wie geht es dir?";

		try (Response response = client.audio
				.create(r -> r.input(input).model(AudioSpeechRequest.Model.TTS_1_HD)
						.voice(AudioSpeechRequest.Voice.ALLOY));
				FileOutputStream fos = new FileOutputStream("hello.mp3");
				var body = response.body();
				var is = body.asInputStream()) {
			is.transferTo(fos);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Path inp = Paths.get("hello.mp3");
		var resp = client.audio.transcriptionsCreate(r -> r.file(inp));
		System.out.println(resp);

		var resp2 = client.audio.translationsCreate(r -> r.file(inp));
		System.out.println(resp2);

	}

}
