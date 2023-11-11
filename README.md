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
    <version>1.0.0</version>
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

    System.out.println(response.choices()[0].message().content());
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

    var message = client.threadsMessages.create(thread.id(),
				    c -> c.role("user").content(
						"I need to solve the equation `3x + 11 = 14`. Can you help me?"));

    var run = client.threadsRuns.create(thread.id(),
            c -> c.assistantId(assistant.id()).instructions(
                    "Please address the user as Jane Doe. The user has a premium account."));

    client.threadsRuns.waitForProcessing(run, 30, TimeUnit.SECONDS, 2, TimeUnit.MINUTES);

    var messages = client.threadsMessages.list(thread.id(), p -> p.before(message.id()));
    for (var msg : messages.data()) {
       var content = msg.content()[0];
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

### 1.0.0 - November 11, 2023
  * Initial release



## License
Code released under [the Apache license](http://www.apache.org/licenses/).
