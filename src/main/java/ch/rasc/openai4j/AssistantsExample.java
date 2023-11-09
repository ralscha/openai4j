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
				.name("ralph")
				.model("gpt-4-1106-preview")
				.instructions("you are a helpul assistant")
				.putMetadata("userId", "1")
				.addTools(RetrievalTool.of())
				);
		System.out.println(c);
		
		Path p = Paths.get("LICENSE");
		var f = client.files.create(r->r.file(p).purpose(Purpose.ASSISTANTS));
		System.out.println(f);
		var r = client.assistantsFiles.create(c.id(), ra->ra.fileId(f.id()));
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
		
		
		
		var m = client.assistants.update(c.id(), ra -> ra.description("my test assistant2")
				.name("ralph2")
				.putMetadata("userId", "2")
				);
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
