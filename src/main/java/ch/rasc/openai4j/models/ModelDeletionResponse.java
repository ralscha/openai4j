package ch.rasc.openai4j.models;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableModelDeletionResponse.class)
public interface ModelDeletionResponse {
	String id();

	String object();

	boolean deleted();
}
