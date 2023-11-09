package ch.rasc.openai4j;

import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import ch.rasc.openai4j.embedding.EmbeddingClient;
import ch.rasc.openai4j.embedding.EmbeddingRequest;
import ch.rasc.openai4j.embedding.EmbeddingRequest.EncodingFormat;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class EmbeddingExample {
	public static void main(String[] args) {
		String token = Util.getToken();

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new Jdk8Module());

		var client = Feign.builder().decoder(new JacksonDecoder(om))
				.encoder(new JacksonEncoder(om))
				.requestInterceptor(new AuthorizationRequestInterceptor(token))
				.target(EmbeddingClient.class, "https://api.openai.com/v1");

		EmbeddingRequest request = EmbeddingRequest.builder().addInput("HelloWorld")
				.addInput("HelloWorld2").encodingFormat(EncodingFormat.BASE64)
				.model("text-embedding-ada-002").build();
		var response = client.embedding(request);
		System.out.println(response.data().get(0).embedding().base64());

		request = EmbeddingRequest.builder().addInput("HelloWorld")
				.addInput("HelloWorld2").encodingFormat(EncodingFormat.FLOAT)
				.model("text-embedding-ada-002").build();
		response = client.embedding(request);
		double[] embeddings = response.data().get(0).embedding().doubleArray();
		System.out.println(Arrays.toString(embeddings));
	}

}
