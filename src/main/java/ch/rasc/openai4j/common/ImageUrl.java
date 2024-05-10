package ch.rasc.openai4j.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageUrl {

	private final String url;
	private final ImageDetail detail;

	public ImageUrl(String url, ImageDetail detail) {
		if (url == null || url.isEmpty()) {
			throw new IllegalArgumentException("url cannot be null");
		}
		this.url = url;
		this.detail = detail;
	}

	/**
	 * Either a URL of the image or the base64 encoded image data.
	 * <p>
	 * The external URL of the image, must be a supported image types: jpeg, jpg, png,
	 * gif, webp
	 */
	@JsonProperty
	public String url() {
		return this.url;
	}

	/**
	 * Specifies the detail level of the image. low uses fewer tokens, you can opt in to
	 * high resolution using high. Default value is auto
	 */
	@JsonProperty
	public ImageDetail detail() {
		return this.detail;
	}
}