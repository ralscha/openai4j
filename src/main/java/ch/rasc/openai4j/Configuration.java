package ch.rasc.openai4j;

import java.util.concurrent.TimeUnit;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import feign.Retryer;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public interface Configuration {

	String apiKey();

	@Nullable
	String organization();

	@Value.Default
	default String baseUrl() {
		return "https://api.openai.com/v1";
	}

	@Value.Default
	default Retryer retryer() {
		return new Retryer.Default(TimeUnit.SECONDS.toMillis(2),
				TimeUnit.SECONDS.toMillis(2), 2);
	}

	// Azure
	/**
	 * The name of your Azure OpenAI Resource.
	 */
	@Nullable
	String azureEndpoint();

	@Nullable
	String apiVersion();

	/**
	 * The deployment name you chose when you deployed the model.
	 */
	@Nullable
	String azureDeployment();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableConfiguration.Builder {
	}
}
