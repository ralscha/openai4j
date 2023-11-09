package ch.rasc.openai4j;

import java.util.ArrayList;
import java.util.List;

import ch.rasc.openai4j.chatcompletions.ChatCompletionsClient;
import ch.rasc.openai4j.files.FilesClient;
import ch.rasc.openai4j.images.ImagesClient;
import feign.Feign;
import feign.RequestInterceptor;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class OpenAIClient {

	public static OpenAIClient create(Configuration configuration) {
			
		OpenAIClient client = new OpenAIClient();
		JacksonDecoder jsonDecoder = new JacksonDecoder();
		JacksonEncoder jsonEncoder = new JacksonEncoder();
		FormEncoder formAndJsonEncoder = new FormEncoder(jsonEncoder);
		
		List<RequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new AuthorizationRequestInterceptor(configuration.apiKey()));
		
		if (configuration.organization() != null && !configuration.organization().isBlank()) {
			interceptors.add(new OpenAIOrganizationRequestInterceptor(configuration.organization()));
		}
		
		client.chatCompletions = Feign.builder().decoder(jsonDecoder)
				.encoder(jsonEncoder)
				.requestInterceptors(interceptors)
				.target(ChatCompletionsClient.class, "https://api.openai.com/v1");
				
		client.files = Feign.builder().decoder(jsonDecoder)
				.encoder(formAndJsonEncoder)
				.requestInterceptors(interceptors)
				.target(FilesClient.class, "https://api.openai.com/v1");
		
		client.images = Feign.builder().decoder(jsonDecoder)
				.encoder(formAndJsonEncoder)
				.requestInterceptors(interceptors)
				.target(ImagesClient.class, "https://api.openai.com/v1");
		
		return client;
	}

	
	public ChatCompletionsClient chatCompletions;
		
	public FilesClient files;
	
	public ImagesClient images;
	
}
