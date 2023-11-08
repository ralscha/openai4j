package ch.rasc.openai4j;

import ch.rasc.openai4j.audio.AudioSpeechRequest;
import ch.rasc.openai4j.audio.AudioTranscriptionRequest;
import ch.rasc.openai4j.audio.AudioTranslationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import feign.Feign;
import feign.Response;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {

	public static void main(String[] args) {
		// read from .env file or read from  environment variable: OPENAI_API_KEY
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

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new Jdk8Module());

		OpenAi openAi = Feign.builder().decoder(new JacksonDecoder(om))
				.encoder(new FormEncoder(new JacksonEncoder(om)))
				// .encoder(new JacksonEncoder(om))
				.requestInterceptor(new AuthorizationRequestInterceptor(token))
				.target(OpenAi.class, "https://api.openai.com/v1");

		var input = """
				Hallo schöne Welt wie geht es dir?
								""";
		var request = AudioSpeechRequest.builder().input(input)
				.model(AudioSpeechRequest.Model.TTS_1_HD)
				.voice(AudioSpeechRequest.Voice.ALLOY).build();

		try (Response response = openAi.audioSpeech(request)) {

			// save response to mp3 file
			try (FileOutputStream fos = new FileOutputStream("hello.mp3");
					var body = response.body();
					var is = body.asInputStream()) {
				is.transferTo(fos);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

		Path inp = Paths.get("hello.mp3");
		var resp = openAi.audioTranscriptions(AudioTranscriptionRequest.builder()
				.file(inp).build());
		System.out.println(resp);
		
		var resp2 = openAi.audioTranslations(AudioTranslationRequest.builder().file(inp).build());
		System.out.println(resp2);

	}

}
