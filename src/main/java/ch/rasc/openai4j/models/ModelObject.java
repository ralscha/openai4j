package ch.rasc.openai4j.models;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableModelObject.class)
public interface ModelObject {

	/**
	 * The model identifier, which can be referenced in the API endpoints.
	 */
	String id();

	/**
	 * The Unix timestamp (in seconds) when the model was created.
	 */
	int created();

	/**
	 * The object type, which is always "model".
	 */
	String object();

	/**
	 * The organization that owns the model.
	 */
	@JsonProperty("owned_by")
	String ownedBy();
}
