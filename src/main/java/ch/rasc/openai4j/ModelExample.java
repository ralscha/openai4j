package ch.rasc.openai4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import ch.rasc.openai4j.model.ModelClient;
import feign.Feign;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class ModelExample {
	public static void main(String[] args) {
		String token = Util.getToken();

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new Jdk8Module());

		var client = Feign.builder().decoder(new JacksonDecoder(om))
				.encoder(new FormEncoder(new JacksonEncoder(om)))
				.requestInterceptor(new AuthorizationRequestInterceptor(token))
				.target(ModelClient.class, "https://api.openai.com/v1");

		var models = client.models();
		for (var response : models.data()) {
			System.out.println(response);
		}

		System.out.println();

		String model = "dall-e-3";
		var modelDetail = client.model(model);
		System.out.println(modelDetail);

		var delete = client.delete("test");
		System.out.println(delete);

		System.out.println();

		models = client.models();
		for (var response : models.data()) {
			System.out.println(response);
		}
	}

}
