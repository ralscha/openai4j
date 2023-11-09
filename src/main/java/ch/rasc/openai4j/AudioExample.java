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
