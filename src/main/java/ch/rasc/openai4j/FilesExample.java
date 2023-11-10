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
import java.nio.file.Paths;

import ch.rasc.openai4j.files.Purpose;

public class FilesExample {
	public static void main(String[] args) {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		var files = client.files.list();
		System.out.println(files);
		for (var response : files.data()) {
			System.out.println(response);

			var dstatus = client.files.delete(response.id());
			System.out.println(dstatus);
		}

		System.out.println();

		var filesWithAPurpose = client.files.list(Purpose.FINE_TUNE);
		System.out.println(filesWithAPurpose);

		var p = Paths.get("./image1.png");
		var response = client.files.create(r -> r.file(p).purpose(Purpose.ASSISTANTS));
		System.out.println(response);

		System.out.println();
		var fileInfo = client.files.retrieve(response.id());
		System.out.println(fileInfo);

		System.out.println();
		files = client.files.list();
		for (var f : files.data()) {
			System.out.println(f);
		}

		try (var resp = client.files.retrieveContent(response.id());
				FileOutputStream fos = new FileOutputStream("image1_copy.png");
				var body = resp.body();
				var is = body.asInputStream()) {
			is.transferTo(fos);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		files = client.files.list();
		for (var r : files.data()) {
			client.files.delete(r.id());
		}

	}

}
