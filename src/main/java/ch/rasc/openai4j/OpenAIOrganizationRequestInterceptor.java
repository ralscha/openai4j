package ch.rasc.openai4j;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class OpenAIOrganizationRequestInterceptor implements RequestInterceptor {

	private final String organization;

	public OpenAIOrganizationRequestInterceptor(String organization) {
		this.organization = organization;
	}

	@Override
	public void apply(RequestTemplate template) {
		template.header("OpenAI-Organization", this.organization);
	}
}