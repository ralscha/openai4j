package ch.rasc.openai4j.image;

import java.io.File;
import java.nio.file.Path;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface Image {

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
				request.mask().map(Path::toFile).orElse(null),
				request.model().map(ImageEditRequest.Model::toValue).orElse(null),
				request.n().orElse(null),
				request.size().map(ImageEditRequest.Size::toValue).orElse(null),
				request.responseFormat().map(ImageEditRequest.ResponseFormat::toValue)
						.orElse(null),
				request.user().orElse(null));
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
				request.model().map(ImageVariationRequest.Model::toValue).orElse(null),
				request.n().orElse(null),
				request.responseFormat()
						.map(ImageVariationRequest.ResponseFormat::toValue).orElse(null),
				request.size().map(ImageVariationRequest.Size::toValue).orElse(null),

				request.user().orElse(null));
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
