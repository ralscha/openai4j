package ch.rasc.openai4j;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class OpenAiOrganizationRequestInterceptor implements RequestInterceptor {

	private String organization;

	public OpenAiOrganizationRequestInterceptor(String organization) {
		this.organization = organization;
	}

	@Override
	public void apply(RequestTemplate template) {
		template.header("OpenAI-Organization", this.organization);
	}
}