package ch.rasc.openai4j.embedding;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Represents an embedding vector returned by embedding endpoint.
 */
@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableEmbeddingObject.class)
public interface EmbeddingObject {

	/**
	 * The index of the embedding in the list of embeddings.
	 */
	int getIndex();

	/**
	 * The embedding vector, which is a list of floats. The length of vector depends on
	 * the model as listed in the embedding guide.
	 */
	double[] getEmbedding();

	/**
	 * The object type, which is always "embedding".
	 */
	String getObject();
}
