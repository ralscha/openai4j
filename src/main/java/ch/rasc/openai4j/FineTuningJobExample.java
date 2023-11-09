package ch.rasc.openai4j;

public class FineTuningJobExample {
	public static void main(String[] args) {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		var jobs = client.fineTuningJobs.list();
		for (var job : jobs.data()) {
			System.out.println(job);

			var j = client.fineTuningJobs.retrieve(job.id());
			System.out.println(j.id());

			var events = client.fineTuningJobs.listEvents(job.id());
			for (var event : events.data()) {
				System.out.println(event);
			}

			// var c = client.cancel(job.id());
			// System.out.println(c);
		}
	}

}
