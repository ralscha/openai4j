package ch.rasc.openai4j.threads;

import com.fasterxml.jackson.annotation.JsonProperty;

import ch.rasc.openai4j.common.ImageDetail;
import ch.rasc.openai4j.common.ImageUrl;

public class ImageUrlMessageContent extends MessageContent {

	private final ImageUrl imageUrl;

	private ImageUrlMessageContent(ImageUrl imageUrl) {
		super("image_url");
		this.imageUrl = imageUrl;
	}

	public static ImageUrlMessageContent of(String url) {
		return new ImageUrlMessageContent(new ImageUrl(url, null));
	}

	public static ImageUrlMessageContent of(String url, ImageDetail detail) {
		return new ImageUrlMessageContent(new ImageUrl(url, detail));
	}

	@JsonProperty("image_url")
	public ImageUrl getImageUrl() {
		return this.imageUrl;
	}
}