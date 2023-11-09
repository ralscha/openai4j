package ch.rasc.openai4j;

public class ModerationsExample {

	public static void main(String[] args) {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		var input = "Hallo schöne Welt wie geht es dir?";
		var response = client.moderations.create(r -> r.input(input));
		System.out.println(response);
	}

}
