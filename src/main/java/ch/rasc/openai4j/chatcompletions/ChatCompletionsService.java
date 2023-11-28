package ch.rasc.openai4j.chatcompletions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;

import ch.rasc.openai4j.chatcompletions.ChatCompletionsResponse.Choice.FinishReason;

/**
 * High level chat completions client that supports calling Java methods
 */
public class ChatCompletionsService {

	private final SchemaGenerator schemaGenerator;

	private final ChatCompletionsClient chatCompletionsClient;

	private final ObjectMapper objectMapper;

	public ChatCompletionsService(ChatCompletionsClient chatCompletionsClient,
			ObjectMapper objectMapper) {
		JacksonModule module = new JacksonModule(
				JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
		SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(
				SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON).with(module);
		SchemaGeneratorConfig config = configBuilder.build();
		this.schemaGenerator = new SchemaGenerator(config);
		this.chatCompletionsClient = chatCompletionsClient;
		this.objectMapper = objectMapper;
	}

	public ChatCompletionsService(ChatCompletionsClient chatCompletionsClient) {
		this(chatCompletionsClient, new ObjectMapper());
	}

	/**
	 * Creates and calls a chat completion for the provided prompt and parameters. When
	 * the response of the completion contains a tool call, the tool call is executed and
	 * the completion is called again with the updated prompt.
	 * <p>
	 * The method will repeat this process until the completion is finished or the maximum
	 * number of iterations is reached.
	 *
	 * @param fn A chat completion request builder function
	 * @param javaFunctions A list of java functions that can be called from the chat
	 * completion
	 * @param maxIterations The maximum number of iterations to call the java functions.
	 * Set it to a reasonable value to avoid infinite loops.
	 * @return A chat completion response
	 * @throws JsonProcessingException If the java function arguments or results cannot be
	 * serialized or deserialized
	 */
	public ChatCompletionsResponse create(
			Function<ChatCompletionsCreateRequest.Builder, ChatCompletionsCreateRequest.Builder> fn,
			List<JavaFunction<?, ?>> javaFunctions, int maxIterations)
			throws JsonProcessingException {
		Map<String, JavaFunction<?, ?>> javaFunctionRegistry = new HashMap<>();
		List<ChatCompletionTool> tools = new ArrayList<>();
		for (JavaFunction<?, ?> javaFunction : javaFunctions) {
			javaFunctionRegistry.put(javaFunction.name(), javaFunction);
			tools.add(javaFunction.toTool(this.schemaGenerator));
		}

		var request = fn.apply(ChatCompletionsCreateRequest.builder()).tools(tools)
				.build();
		ChatCompletionsResponse response = this.chatCompletionsClient.create(request);

		var thread = new ArrayList<>(request.messages());
		var choice = response.choices().get(0);

		int iterationCount = 1;
		while (choice.finishReason() == FinishReason.TOOL_CALLS) {
			if (iterationCount > maxIterations) {
				return response;
			}

			var message = choice.message();
			thread.add(AssistantMessage.of(choice.message()));

			for (var toolCall : message.toolCalls()) {
				JavaFunction<?, ?> javaFunction = javaFunctionRegistry
						.get(toolCall.function().name());
				if (javaFunction == null) {
					throw new IllegalStateException(
							"Unknown function " + toolCall.function().name());
				}
				var argument = this.objectMapper.readValue(
						toolCall.function().arguments(), javaFunction.parameterClass());
				Object result = javaFunction.call(argument);

				if (result != null) {
					String resultJson = this.objectMapper.writeValueAsString(result);
					thread.add(ToolMessage.of(toolCall.id(), resultJson));
				}
				else {
					thread.add(ToolMessage.of(toolCall.id(), null));
				}
			}

			request = request.withMessages(thread);
			response = this.chatCompletionsClient.create(request);

			iterationCount += 1;
		}

		return response;
	}
}
