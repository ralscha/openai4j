package ch.rasc.openai4j;

import java.io.IOException;
import java.io.Serial;

public class OpenAIApiException extends IOException {
	@Serial
	private static final long serialVersionUID = 1L;

	private final String message;
	private final String type;
	private final String param;
	private final String code;

	public OpenAIApiException(String message, String type, String param, String code) {
		super(type + ": " + message);
		this.message = message;
		this.type = type;
		this.param = param;
		this.code = code;
	}

	public String message() {
		return this.message;
	}

	public String type() {
		return this.type;
	}

	public String param() {
		return this.param;
	}

	public String code() {
		return this.code;
	}

}
