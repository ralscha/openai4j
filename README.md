# openai4j

Unofficial, community-maintained Java implementation of the OpenAI API:

https://platform.openai.com/docs/api-reference

openai4j is a Java library that implements all currently (as of 10.11.2023) [documented endpoints](https://platform.openai.com/docs/api-reference)
including the endpoints that are only available in beta. Legacy and deprecated endpoints are not implemented.

## Installation

```xml
<dependency>
    <groupId>ch.rasc</groupId>
    <artifactId>openai4j</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>

<!-- add this to use the snapshot version -->
<repository>
    <id>sonatype-snapshots</id>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
</repository>
```


## Usage

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

Check out the [openai4j-examples](https://github.com/ralscha/openai4j-examples) repository for more examples.


## Changelog

Not yet released


## License
Code released under [the Apache license](http://www.apache.org/licenses/).
