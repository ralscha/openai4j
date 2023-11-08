package ch.rasc.openai4j;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class ApiVersionRequestInterceptor implements RequestInterceptor {
	private final String version;

	public ApiVersionRequestInterceptor(String version) {
		this.version = version;
	}

	@Override
	public void apply(RequestTemplate template) {
		template.query("api-version", this.version);
	}
}
