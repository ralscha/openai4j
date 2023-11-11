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

### 1.0.0 - November 11, 2023
  * Initial release



## License
Code released under [the Apache license](http://www.apache.org/licenses/).
