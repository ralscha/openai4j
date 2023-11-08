package ch.rasc.openai4j.model;

import java.util.List;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableModelsResponse.class)
public interface ModelsResponse {
	
	String object();
	
	List<ModelObject> data();
}
