package ch.rasc.openai4j;

import java.util.Optional;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public interface Configuration {

	String apiKey();

	Optional<String> organization();

	// Azure
	Optional<String> baseUrl();

	Optional<String> apiVersion();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableConfiguration.Builder {
	}
}
