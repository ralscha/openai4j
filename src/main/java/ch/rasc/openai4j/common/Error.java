package ch.rasc.openai4j.common;

import ch.rasc.openai4j.Nullable;

/**
 * @param code A machine-readable error code.
 * @param message A human-readable description of the error.
 * @param param For fine-tuning. The parameter that was invalid, usually training_file or
 * validation_file. This field will be null if the failure was not parameter-specific.
 */
public record Error(String code, String message, @Nullable String param) {
}
