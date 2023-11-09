package ch.rasc.openai4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import ch.rasc.openai4j.chat.ChatClient;
import ch.rasc.openai4j.chat.ChatCompletionRequest;
import ch.rasc.openai4j.chat.SystemMessage;
import ch.rasc.openai4j.chat.UserMessage;
import feign.Feign;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class ChatExample {
	public static void main(String[] args) throws JsonProcessingException {
		String token = Util.getToken();

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new Jdk8Module());

		var client = Feign.builder().decoder(new JacksonDecoder(om))
				.encoder(new FormEncoder(new JacksonEncoder(om)))
				.requestInterceptor(new AuthorizationRequestInterceptor(token))
				.target(ChatClient.class, "https://api.openai.com/v1");

		var request = ChatCompletionRequest.builder()
				.addMessage(SystemMessage.of("You are a helpful assistant"))
				.addMessage(UserMessage.of("What is the capital of Spain?"))
				.model("gpt-4-1106-preview").build();
		System.out.println(om.writeValueAsString(request));

		var response = client.completion(request);
		System.out.println(response);
	}

}
