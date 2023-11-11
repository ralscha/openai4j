package ch.rasc.openai4j.common;

/**
 * @param name The name of the function.
 * @param arguments The arguments that the model expects you to pass to the function.
 */
public record FunctionArguments(String name, String arguments) {

}
