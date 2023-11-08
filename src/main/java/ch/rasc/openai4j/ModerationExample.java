package ch.rasc.openai4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import ch.rasc.openai4j.moderation.Moderation;
import ch.rasc.openai4j.moderation.ModerationRequest;
import feign.Feign;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class ModerationExample {

	public static void main(String[] args) {
		String token = Util.getToken();

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new Jdk8Module());

		var client = Feign.builder().decoder(new JacksonDecoder(om))
				.encoder(new FormEncoder(new JacksonEncoder(om)))
				.requestInterceptor(new AuthorizationRequestInterceptor(token))
				.target(Moderation.class, "https://api.openai.com/v1");

		var input = "Hallo schöne Welt wie geht es dir?";
		var response = client
				.moderation(ModerationRequest.builder().input(input).build());
		System.out.println(response);
	}

}
