# openai4j

Unofficial, community-maintained Java implementation of the OpenAI API:

https://platform.openai.com/docs/api-reference

openai4j is a Java library that implements all  [documented endpoints](https://platform.openai.com/docs/api-reference) as of 11.11.2023, 
including the endpoints that are only available in beta.  
Legacy and deprecated endpoints are not implemented.

## Installation

```xml
<dependency>
  <groupId>ch.rasc</groupId>
  <artifactId>openai4j</artifactId>
  <version>1.0.2</version>
</dependency>
```


## Usage

### Chat completions
```java
  String apiKey = ... // read OpenAI api key from environment variable
  var client = OpenAIClient.create(c -> c.apiKey(apiKey));

  var response = client.chatCompletions
  .create(r -> 
     r.addMessage(SystemMessage.of("You are a helpful assistant"))
    .addMessage(UserMessage.of("What is the capital of Spain?"))
    .model("gpt-4-1106-preview"));

  System.out.println(response.choices().get(0).message().content());
```

### Function calling with Java code

```java
public class ChatCompletionsFunctionExample {
  private final static ObjectMapper om = new ObjectMapper();

  static class Location {
  @JsonProperty(required = true)
  @JsonPropertyDescription("Latitude of the location. Geographical WGS84 coordinates")
  public float latitude;

  @JsonProperty(required = true)
  @JsonPropertyDescription("Longitude of the location. Geographical WGS84 coordinates")
  public float longitude;
  }

  static class TemperatureFetcher {

  public Float fetchTemperature(Location location) {
    try (var client = HttpClient.newHttpClient()) {
        var request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.open-meteo.com/v1/metno?latitude="
                + location.latitude + "&longitude=" + location.longitude
                + "&current=temperature_2m"))
            .build();
        var response = client.send(request,
            java.net.http.HttpResponse.BodyHandlers.ofString());

        var body = response.body();
        var jsonNode = om.readTree(body);
        var current = jsonNode.get("current");
        var temperature = current.get("temperature_2m");
        return temperature.floatValue();
      }
      catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public static void main(String[] args) throws JsonProcessingException {

    String apiKey = ... // read OpenAI api key from environment variable
    var client = OpenAIClient.create(c -> c.apiKey(apiKey));

    TemperatureFetcher fetcher = new TemperatureFetcher();
    JavaFunction<Location, Float> getWeather = JavaFunction.of("get_temperature",
        "Get the current temperature of a location", Location.class,
        fetcher::fetchTemperature);

    var response = client.chatCompletions.create(r -> r.addMessage(UserMessage.of(
        "What are the current temperatures in Oslo, Norway and Helsinki, Finland?"))
        .model("gpt-4-1106-preview"), List.of(getWeather), om, 1);

    var choice = response.choices().get(0);
    System.out.println(choice.message().content());
  }
}
```


### Asisstant

```java
  String apiKey = ... // read OpenAI api key from environment variable
  var client = OpenAIClient.create(c -> c.apiKey(apiKey));

  Assistant assistant = client.assistants.create(c -> c.name("Math Tutor").instructions(
      "You are a personal math tutor. Write and run code to answer math questions.")
      .addTools(CodeTool.of()).model("gpt-4-1106-preview"));
  }

  var thread = client.threads.create();

  var message = client.threadsMessages.create(thread.id(), c -> c.content(
        "I need to solve the equation `3x + 11 = 14`. Can you help me?"));

  var run = client.threadsRuns.create(thread.id(),
      c -> c.assistantId(assistant.id()).instructions(
          "Please address the user as Jane Doe. The user has a premium account."));

  client.threadsRuns.waitForProcessing(run, 30, TimeUnit.SECONDS, 2, TimeUnit.MINUTES);

  var messages = client.threadsMessages.list(thread.id(), p -> p.before(message.id()));
  for (var msg : messages.data()) {
     var content = msg.content().get(0);
     if (content instanceof MessageContentText text) {
       System.out.println(text.text().value());
     }
  }
```

### Azure

```java
  String apiKey = ... // read OpenAI api key from environment variable
  String azureEndpoint = ... // "https://myresource.openai.azure.com/"
  var azureClient = OpenAIClient
     .create(Configuration.builder()
     .apiVersion("2023-07-01-preview")
     .apiKey(apiKey)
     .azureDeployment("gpt-35-turbo")
     .azureEndpoint(azureEndpoint)
     .build());
```     

Check out the [openai4j-examples](https://github.com/ralscha/openai4j-examples) repository for more examples.

## Changelog

### 1.0.3 - November 13, 2023
  * Add `name` property to Chat Completions SystemMessage and UserMessage
  * More JavaDoc
  * Replace arrays with List
  
### 1.0.2 - November 12, 2023
  * Bugfix: SubmitToolOutputs not properly JSON decoded
  * Bugfix: waitForProcessing in ThreadsRuns must return the last ThreadRun object

### 1.0.1 - November 12, 2023
  * Bugfix: ChatCompletions UserMessage for GPT Vision

### 1.0.0 - November 11, 2023
  * Initial release



## License
Code released under [the Apache license](http://www.apache.org/licenses/).
