package ch.rasc.openai4j;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class AzureApiKeyRequestInterceptor implements RequestInterceptor {

	private final String apiKey;

	public AzureApiKeyRequestInterceptor(String apiKey) {
		this.apiKey = apiKey;
	}

	@Override
	public void apply(RequestTemplate template) {
		template.header("api-key", this.apiKey);
	}
}