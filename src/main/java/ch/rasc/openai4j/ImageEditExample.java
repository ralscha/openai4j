package ch.rasc.openai4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import ch.rasc.openai4j.images.ImagesClient;
import ch.rasc.openai4j.images.ImageEditRequest;
import ch.rasc.openai4j.images.ImageObject;
import ch.rasc.openai4j.images.ImageEditRequest.Size;
import feign.Feign;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class ImageEditExample {

	public static void main(String[] args) throws IOException {
		String token = Util.getApiKey();

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new Jdk8Module());

		var client = Feign.builder().decoder(new JacksonDecoder(om))
				.encoder(new FormEncoder(new JacksonEncoder(om)))
				.requestInterceptor(new AuthorizationRequestInterceptor(token))
				.target(ImagesClient.class, "https://api.openai.com/v1");

		var response = client
				.imageEdit(ImageEditRequest.builder().image(Paths.get("./input.png"))
						.prompt("Cats in Paris").mask(Paths.get("./mask.png")).n(3)
						.responseFormat(ImageEditRequest.ResponseFormat.B64_JSON)
						.size(Size.S_1024).build());
		int i = 7;
		for (ImageObject imageObject : response.data()) {
			String b64Json = imageObject.b64Json();
			byte[] decodedBytes = Base64.getDecoder().decode(b64Json);
			Files.write(Paths.get("image" + i + ".png"), decodedBytes);
			i++;
		}

	}

}
