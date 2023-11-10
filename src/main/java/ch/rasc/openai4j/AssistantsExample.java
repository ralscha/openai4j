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

import java.nio.file.Path;
import java.nio.file.Paths;

import ch.rasc.openai4j.assistants.RetrievalTool;
import ch.rasc.openai4j.files.Purpose;

public class AssistantsExample {
	public static void main(String[] args) {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		var assistants = client.assistants.list();
		for (var a : assistants.data()) {
			System.out.println(a);
		}

		var c = client.assistants.create(r -> r.description("my test assistant")
				.name("ralph").model("gpt-4-1106-preview")
				.instructions("you are a helpul assistant").putMetadata("userId", "1")
				.addTools(RetrievalTool.of()));
		System.out.println(c);

		Path p = Paths.get("LICENSE");
		var f = client.files.create(r -> r.file(p).purpose(Purpose.ASSISTANTS));
		System.out.println(f);
		var r = client.assistantsFiles.create(c.id(), ra -> ra.fileId(f.id()));
		System.out.println(r);

		var r2 = client.assistantsFiles.list(c.id());
		System.out.println(r2);

		var r3 = client.assistantsFiles.retrieve(c.id(), f.id());
		System.out.println(r3);

		var d = client.assistantsFiles.delete(c.id(), f.id());
		System.out.println(d);

		var d2 = client.files.delete(f.id());
		System.out.println(d2);

		var ll = client.files.list();
		for (var lll : ll.data()) {
			var ddd = client.files.delete(lll.id());
			System.out.println(ddd);
		}

		var m = client.assistants.update(c.id(),
				ra -> ra.description("my test assistant2").name("ralph2")
						.putMetadata("userId", "2"));
		System.out.println(m);

		var m2 = client.assistants.retrieve(m.id());
		System.out.println(m2);

		assistants = client.assistants.list();
		for (var a : assistants.data()) {
			client.assistants.delete(a.id());
		}
		System.out.println("LIST");
		assistants = client.assistants.list();
		for (var a : assistants.data()) {
			System.out.println(a);
		}

	}

}
