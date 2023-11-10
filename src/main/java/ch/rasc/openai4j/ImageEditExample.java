package ch.rasc.openai4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import ch.rasc.openai4j.images.Image;
import ch.rasc.openai4j.images.ImageEditRequest;
import ch.rasc.openai4j.images.ImageEditRequest.Size;

public class ImageEditExample {

	public static void main(String[] args) throws IOException {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		var response = client.images.edit(r -> r.image(Paths.get("./input.png"))
				.prompt("Cats in Paris").mask(Paths.get("./mask.png")).n(3)
				.responseFormat(ImageEditRequest.ResponseFormat.B64_JSON)
				.size(Size.S_1024));
		int i = 7;
		for (Image imageObject : response.data()) {
			String b64Json = imageObject.b64Json();
			byte[] decodedBytes = Base64.getDecoder().decode(b64Json);
			Files.write(Paths.get("image" + i + ".png"), decodedBytes);
			i++;
		}

	}

}
