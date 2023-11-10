package ch.rasc.openai4j;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class AuthorizationRequestInterceptor implements RequestInterceptor {

	private final String apiKey;

	public AuthorizationRequestInterceptor(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public void apply(RequestTemplate template) {
		template.header("Authorization", "Bearer " + this.apiKey);
	}
}