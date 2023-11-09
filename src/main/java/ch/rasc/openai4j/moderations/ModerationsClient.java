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
	ModerationsResponse create(ModerationsRequest request);

	/**
	 * Classifies if text violates OpenAI's Content Policy
	 *
	 * @return A moderation object.
	 */
	default ModerationsResponse create(
			Function<ModerationsRequest.Builder, ModerationsRequest.Builder> fn) {
		return this.create(fn.apply(ModerationsRequest.builder()).build());
	}

}
