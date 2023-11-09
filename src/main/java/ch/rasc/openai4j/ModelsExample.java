package ch.rasc.openai4j;

public class ModelsExample {
	public static void main(String[] args) {
		String apiKey = Util.getApiKey();
		var client = OpenAIClient.create(Configuration.builder().apiKey(apiKey).build());

		var models = client.models.list();
		for (var response : models.data()) {
			System.out.println(response);
		}

		System.out.println();

		String model = "dall-e-3";
		var modelDetail = client.models.retrieve(model);
		System.out.println(modelDetail);

		var delete = client.models.delete("test");
		System.out.println(delete);

		System.out.println();

		models = client.models.list();
		for (var response : models.data()) {
			System.out.println(response);
		}
	}

}
