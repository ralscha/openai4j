package ch.rasc.openai4j;

import java.util.ArrayList;
import java.util.List;

import ch.rasc.openai4j.assistants.AssistantsClient;
import ch.rasc.openai4j.audio.AudioClient;
import ch.rasc.openai4j.chatcompletions.ChatCompletionsClient;
import ch.rasc.openai4j.embeddings.EmbeddingsClient;
import ch.rasc.openai4j.files.FilesClient;
import ch.rasc.openai4j.finetuningjobs.FineTuningJobsClient;
import ch.rasc.openai4j.images.ImagesClient;
import ch.rasc.openai4j.models.ModelsClient;
import ch.rasc.openai4j.moderations.ModerationsClient;
import ch.rasc.openai4j.threads.ThreadsClient;
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

		if (configuration.organization() != null
				&& !configuration.organization().isBlank()) {
			interceptors.add(new OpenAIOrganizationRequestInterceptor(
					configuration.organization()));
		}

		client.chatCompletions = Feign.builder().decoder(jsonDecoder).encoder(jsonEncoder)
				.requestInterceptors(interceptors)
				.target(ChatCompletionsClient.class, "https://api.openai.com/v1");

		client.embeddings = Feign.builder().decoder(jsonDecoder).encoder(jsonEncoder)
				.requestInterceptors(interceptors)
				.target(EmbeddingsClient.class, "https://api.openai.com/v1");

		client.files = Feign.builder().decoder(jsonDecoder).encoder(formAndJsonEncoder)
				.requestInterceptors(interceptors)
				.target(FilesClient.class, "https://api.openai.com/v1");

		client.fineTuningJobs = Feign.builder().decoder(jsonDecoder)
				.encoder(formAndJsonEncoder).requestInterceptors(interceptors)
				.target(FineTuningJobsClient.class, "https://api.openai.com/v1");

		client.audio = Feign.builder().decoder(jsonDecoder).encoder(formAndJsonEncoder)
				.requestInterceptors(interceptors)
				.target(AudioClient.class, "https://api.openai.com/v1");

		client.images = Feign.builder().decoder(jsonDecoder).encoder(formAndJsonEncoder)
				.requestInterceptors(interceptors)
				.target(ImagesClient.class, "https://api.openai.com/v1");

		client.moderations = Feign.builder().decoder(jsonDecoder).encoder(jsonEncoder)
				.requestInterceptors(interceptors)
				.target(ModerationsClient.class, "https://api.openai.com/v1");

		client.models = Feign.builder().decoder(jsonDecoder).encoder(jsonEncoder)
				.requestInterceptors(interceptors)
				.target(ModelsClient.class, "https://api.openai.com/v1");

		var betaInterceptors = new ArrayList<>(interceptors);
		betaInterceptors.add(new OpenAIBetaRequestInterceptor());
		client.threads = Feign.builder().decoder(jsonDecoder).encoder(jsonEncoder)
				.requestInterceptors(betaInterceptors)
				.target(ThreadsClient.class, "https://api.openai.com/v1");
		client.assistants = Feign.builder().decoder(jsonDecoder).encoder(jsonEncoder)
				.requestInterceptors(betaInterceptors)
				.target(AssistantsClient.class, "https://api.openai.com/v1");
		return client;
	}

	public AudioClient audio;

	public ChatCompletionsClient chatCompletions;

	public EmbeddingsClient embeddings;

	public FilesClient files;

	public FineTuningJobsClient fineTuningJobs;

	public ImagesClient images;

	public ModelsClient models;

	public ModerationsClient moderations;

	public ThreadsClient threads;

	public AssistantsClient assistants;

}
