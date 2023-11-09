package ch.rasc.openai4j.files;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.files.ImmutableFileDeletionResponse;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableFileDeletionResponse.class)
public interface FileDeletionResponse {
	String id();

	String object();

	boolean deleted();
}
