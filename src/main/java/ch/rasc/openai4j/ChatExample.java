package ch.rasc.openai4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import ch.rasc.openai4j.chat.ChatClient;
import ch.rasc.openai4j.chat.ChatCompletionRequest.ToolMessage;
import feign.Feign;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class ChatExample {
	public static void main(String[] args) throws JsonProcessingException {
		String token = Util.getToken();

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new Jdk8Module());

		Feign.builder().decoder(new JacksonDecoder(om))
				.encoder(new FormEncoder(new JacksonEncoder(om)))
				.requestInterceptor(new AuthorizationRequestInterceptor(token))
				.target(ChatClient.class, "https://api.openai.com/v1");

		ToolMessage toolMessage = ToolMessage.builder().content(null).toolCallId("hi")
				.build();
		System.out.println(toolMessage);

	}

}
