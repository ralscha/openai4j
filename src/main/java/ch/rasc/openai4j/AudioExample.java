package ch.rasc.openai4j;

import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import ch.rasc.openai4j.audio.Audio;
import ch.rasc.openai4j.audio.AudioSpeechRequest;
import ch.rasc.openai4j.audio.AudioTranscriptionRequest;
import ch.rasc.openai4j.audio.AudioTranslationRequest;
import feign.Feign;
import feign.Response;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class AudioExample {

	public static void main(String[] args) {
		String token = Util.getToken();

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new Jdk8Module());

		var client = Feign.builder().decoder(new JacksonDecoder(om))
				.encoder(new FormEncoder(new JacksonEncoder(om)))
				.requestInterceptor(new AuthorizationRequestInterceptor(token))
				.target(Audio.class, "https://api.openai.com/v1");

		var input = "Hallo schöne Welt wie geht es dir?";
		var request = AudioSpeechRequest.builder().input(input)
				.model(AudioSpeechRequest.Model.TTS_1_HD)
				.voice(AudioSpeechRequest.Voice.ALLOY).build();

		try (Response response = client.audioSpeech(request);
				FileOutputStream fos = new FileOutputStream("hello.mp3");
				var body = response.body();
				var is = body.asInputStream()) {
			is.transferTo(fos);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Path inp = Paths.get("hello.mp3");
		var resp = client.audioTranscription(
				AudioTranscriptionRequest.builder().file(inp).build());
		System.out.println(resp);

		var resp2 = client
				.audioTranslation(AudioTranslationRequest.builder().file(inp).build());
		System.out.println(resp2);

	}

}
