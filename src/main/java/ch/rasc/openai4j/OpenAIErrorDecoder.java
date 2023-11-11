package ch.rasc.openai4j;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;

public class OpenAIErrorDecoder implements ErrorDecoder {

	private final ObjectMapper objectMapper;

	private final ErrorDecoder.Default defaultErrorDecoder;

	public OpenAIErrorDecoder() {
		this.objectMapper = new ObjectMapper();
		this.defaultErrorDecoder = new ErrorDecoder.Default();
	}

	@Override
	public Exception decode(String methodKey, Response response) {

		try (var body = response.body(); var is = body.asInputStream()) {
			Map<String, Object> error = this.objectMapper.readValue(is, Map.class);
			if (error.containsKey("error") && error.get("error") instanceof Map) {
				Map<String, Object> errorMap = (Map<String, Object>) error.get("error");
				String message = (String) errorMap.get("message");
				String type = (String) errorMap.get("type");
				String param = (String) errorMap.get("param");
				String code = (String) errorMap.get("code");

				return new OpenAIApiException(message, type, param, code);
			}
		}
		catch (IOException e) {
			// do nothing fall back to default error decoder
		}

		return this.defaultErrorDecoder.decode(methodKey, response);
	}

}
