package ch.rasc.openai4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import ch.rasc.openai4j.images.Image;
import ch.rasc.openai4j.images.ImageVariationRequest;
import ch.rasc.openai4j.images.ImageVariationRequest.Size;

public class ImageVariationExample {

	public static void main(String[] args) throws IOException {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		var response = client.images.createVariation(
				ImageVariationRequest.builder().image(Paths.get("./image2.png"))
						.model(ImageVariationRequest.Model.DALL_E_2).n(4)
						.responseFormat(ImageVariationRequest.ResponseFormat.B64_JSON)
						.size(Size.S_1024).build());
		int i = 3;
		for (Image imageObject : response.data()) {
			String b64Json = imageObject.b64Json();
			byte[] decodedBytes = Base64.getDecoder().decode(b64Json);
			Files.write(Paths.get("image" + i + ".png"), decodedBytes);
			i++;
		}

	}

}
