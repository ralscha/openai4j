package ch.rasc.openai4j.images;

import java.io.File;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface ImagesClient {

	/**
	 * Creates an image given a prompt.
	 *
	 * @return Returns a list of image objects.
	 */
	@RequestLine("POST /images/generations")
	@Headers("Content-Type: application/json")
	ImageResponse imageGeneration(ImageGenerationRequest request);

	/**
	 * Creates an edited or extended image given an original image and a prompt.
	 *
	 * @return Returns a list of image objects.
	 */
	default ImageResponse imageEdit(ImageEditRequest request) {
		return this.imageEdit(request.image().toFile(), request.prompt(),
				request.mask() != null ? request.mask().toFile() : null,
				request.model() != null ? request.model().toValue() : null, request.n(),
				request.size() != null ? request.size().toValue() : null,
				request.responseFormat() != null ? request.responseFormat().toValue()
						: null,
				request.user());
	}

	/**
	 * Creates an edited or extended image given an original image and a prompt.
	 *
	 * @return Returns a list of image objects.
	 */
	@RequestLine("POST /images/edits")
	@Headers("Content-Type: multipart/form-data")
	ImageResponse imageEdit(@Param("image") File image, @Param("prompt") String prompt,
			@Param("mask") File mask, @Param("model") String model, @Param("n") Integer n,
			@Param("size") String size, @Param("response_format") String responseFormat,
			@Param("user") String user);

	/**
	 * Creates a variation of a given image.
	 *
	 * @return Returns a list of image objects.
	 */
	default ImageResponse imageVariation(ImageVariationRequest request) {
		return this.imageVariation(request.image().toFile(),
				request.model() != null ? request.model().toValue() : null, request.n(),
				request.responseFormat() != null ? request.responseFormat().toValue()
						: null,
				request.size() != null ? request.size().toValue() : null, request.user());
	}

	/**
	 * Creates a variation of a given image.
	 *
	 * @return Returns a list of image objects.
	 */
	@RequestLine("POST /images/variations")
	@Headers("Content-Type: multipart/form-data")
	ImageResponse imageVariation(@Param("image") File image, @Param("model") String model,
			@Param("n") Integer n, @Param("response_format") String responseFormat,
			@Param("size") String size, @Param("user") String user);
}
