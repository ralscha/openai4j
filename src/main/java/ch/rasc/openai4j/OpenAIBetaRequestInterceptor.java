package ch.rasc.openai4j;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class OpenAIBetaRequestInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		template.header("OpenAI-Beta", "assistants=v1");
	}
}