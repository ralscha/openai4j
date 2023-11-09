package ch.rasc.openai4j;

import java.util.Arrays;

import ch.rasc.openai4j.embeddings.EmbeddingsRequest;
import ch.rasc.openai4j.embeddings.EmbeddingsRequest.EncodingFormat;

public class EmbeddingsExample {
	public static void main(String[] args) {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		EmbeddingsRequest request = EmbeddingsRequest.builder().addInput("HelloWorld")
				.addInput("HelloWorld2").encodingFormat(EncodingFormat.BASE64)
				.model("text-embedding-ada-002").build();
		var response = client.embeddings.create(request);
		System.out.println(response.data().get(0).embedding().base64());

		response = client.embeddings.create(r -> r.addInput("HelloWorld")
				.addInput("HelloWorld2").encodingFormat(EncodingFormat.FLOAT)
				.model("text-embedding-ada-002"));
		double[] embeddings = response.data().get(0).embedding().doubleArray();
		System.out.println(Arrays.toString(embeddings));
	}

}
