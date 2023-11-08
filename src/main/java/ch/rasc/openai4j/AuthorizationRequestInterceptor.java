package ch.rasc.openai4j;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class AuthorizationRequestInterceptor implements RequestInterceptor {

	private final String token;

	public AuthorizationRequestInterceptor(String token) {
		this.token = token;
	}

	@Override
	public void apply(RequestTemplate template) {
		template.header("Authorization", "Bearer " + this.token);
	}
}