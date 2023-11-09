package ch.rasc.openai4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import ch.rasc.openai4j.finetuning.FineTuningClient;
import feign.Feign;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class FineTuningExample {
	public static void main(String[] args) {
		String token = Util.getApiKey();

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new Jdk8Module());

		var client = Feign.builder().decoder(new JacksonDecoder(om))
				.encoder(new FormEncoder(new JacksonEncoder(om)))
				.requestInterceptor(new AuthorizationRequestInterceptor(token))
				.target(FineTuningClient.class, "https://api.openai.com/v1");

		var jobs = client.jobs();
		for (var job : jobs.data()) {
			System.out.println(job);

			var j = client.job(job.id());
			System.out.println(j.id());

			var events = client.jobEvents(job.id());
			for (var event : events.data()) {
				System.out.println(event);
			}

			// var c = client.cancel(job.id());
			// System.out.println(c);
		}
	}

}
