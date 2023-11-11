/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.rasc.openai4j;

import java.util.concurrent.TimeUnit;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import feign.Logger;
import feign.Logger.Level;
import feign.Request;
import feign.Retryer;
import feign.slf4j.Slf4jLogger;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
public interface Configuration {

	static Builder builder() {
		return new Builder();
	}

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
				TimeUnit.SECONDS.toMillis(2), 3);
	}

	@Value.Default
	default Request.Options feignOptions() {
		return new Request.Options(10, TimeUnit.SECONDS, 60, TimeUnit.SECONDS, true);
	}

	@Value.Default
	default Logger logger() {
		return new Slf4jLogger();
	}

	// Azure

	@Value.Default
	default Level logLevel() {
		return Level.NONE;
	}

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

	final class Builder extends ImmutableConfiguration.Builder {
	}
}
