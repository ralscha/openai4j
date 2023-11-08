package ch.rasc.openai4j.model;

import feign.Param;
import feign.RequestLine;

public interface ModelClient {

	/**
	 * Lists the currently available models, and provides basic information about each one
	 * such as the owner and availability.
	 *
	 * @return Returns a list of model objects.
	 */
	@RequestLine("GET /models")
	ModelsResponse models();

	/**
	 * Retrieves a model instance, providing basic information about the model such as the
	 * owner and permissioning.
	 *
	 * @return The model object matching the specified ID.
	 */
	@RequestLine("GET /models/{model}")
	ModelObject model(@Param("model") String model);

	/**
	 * Delete a fine-tuned model. You must have the Owner role in your organization to
	 * delete a model.
	 *
	 * @return Deletion status.
	 */
	@RequestLine("DELETE /models/{model}")
	ModelDeletionResponse delete(@Param("model") String model);

}
