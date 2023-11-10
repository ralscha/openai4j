package ch.rasc.openai4j.models;

import ch.rasc.openai4j.common.DeletionStatus;
import feign.Param;
import feign.RequestLine;

public interface ModelsClient {

	/**
	 * Lists the currently available models, and provides basic information about each one
	 * such as the owner and availability.
	 *
	 * @return Returns a list of model objects.
	 */
	@RequestLine("GET /models")
	ModelsResponse list();

	/**
	 * Retrieves a model instance, providing basic information about the model such as the
	 * owner and permissioning.
	 *
	 * @return The model object matching the specified ID.
	 */
	@RequestLine("GET /models/{model}")
	Model retrieve(@Param("model") String model);

	/**
	 * Delete a fine-tuned model. You must have the Owner role in your organization to
	 * delete a model.
	 *
	 * @return Deletion status.
	 */
	@RequestLine("DELETE /models/{model}")
	DeletionStatus delete(@Param("model") String model);

}
