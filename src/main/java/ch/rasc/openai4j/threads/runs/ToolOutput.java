package ch.rasc.openai4j.threads.runs;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ToolOutput(@JsonProperty("tool_call_id") String toolCallId, String output) {
}