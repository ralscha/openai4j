package ch.rasc.openai4j;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public interface Configuration {

	String apiKey();

	@Nullable
	String organization();

	// Azure
	@Nullable
	String baseUrl();

	@Nullable
	String apiVersion();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableConfiguration.Builder {
	}
}
