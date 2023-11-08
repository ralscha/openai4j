package ch.rasc.openai4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import ch.rasc.openai4j.image.Image;
import ch.rasc.openai4j.image.ImageGenerationRequest;
import ch.rasc.openai4j.image.ImageGenerationRequest.Size;
import feign.Feign;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class ImageGenerationExample {

	public static void main(String[] args) throws IOException, InterruptedException {
		String token = Util.getToken();

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new Jdk8Module());

		var client = Feign.builder().decoder(new JacksonDecoder(om))
				.encoder(new FormEncoder(new JacksonEncoder(om)))
				.requestInterceptor(new AuthorizationRequestInterceptor(token))
				.target(Image.class, "https://api.openai.com/v1");

		var input = "A bunch of people are standing in a field. They are wearing colorful clothes and holding umbrellas.";
		var response = client.imageGeneration(ImageGenerationRequest.builder()
				.model(ImageGenerationRequest.Model.DALL_E_3)
				.quality(ImageGenerationRequest.Quality.HD).prompt(input)
				.style(ImageGenerationRequest.Style.NATURAL).size(Size.S_1024).build());
		var url = response.data().get(0).url();

		try (var httpClient = HttpClient.newHttpClient()) {
			var request = HttpRequest.newBuilder().uri(URI.create(url.get())).build();
			var resp = httpClient.send(request,
					HttpResponse.BodyHandlers.ofInputStream());
			var fileName = "image1.png";
			try (var body = resp.body()) {
				Files.copy(body, Paths.get(fileName),
						StandardCopyOption.REPLACE_EXISTING);
			}
		}

		input = "A couple of cats are sitting on a couch.";
		response = client.imageGeneration(ImageGenerationRequest.builder()
				.model(ImageGenerationRequest.Model.DALL_E_3)
				.quality(ImageGenerationRequest.Quality.HD).prompt(input)
				.style(ImageGenerationRequest.Style.NATURAL)
				.responseFormat(ImageGenerationRequest.ResponseFormat.B64_JSON)
				.size(Size.S_1024).build());
		String b64Json = response.data().get(0).b64Json().get();
		byte[] decodedBytes = Base64.getDecoder().decode(b64Json);
		Files.write(Paths.get("image2.png"), decodedBytes);

	}

}
