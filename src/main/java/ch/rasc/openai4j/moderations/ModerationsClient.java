package ch.rasc.openai4j.moderations;

import java.util.function.Function;

import feign.Headers;
import feign.RequestLine;

public interface ModerationsClient {
	/**
	 * Classifies if text violates OpenAI's Content Policy
	 *
	 * @return A moderation object.
	 */
	@RequestLine("POST /moderations")
	@Headers("Content-Type: application/json")
	ModerationsCreateResponse create(ModerationsCreateRequest request);

	/**
	 * Classifies if text violates OpenAI's Content Policy
	 *
	 * @return A moderation object.
	 */
	default ModerationsCreateResponse create(
			Function<ModerationsCreateRequest.Builder, ModerationsCreateRequest.Builder> fn) {
		return this.create(fn.apply(ModerationsCreateRequest.builder()).build());
	}

}
