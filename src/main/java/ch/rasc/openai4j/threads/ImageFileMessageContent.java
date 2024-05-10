package ch.rasc.openai4j.threads;

import com.fasterxml.jackson.annotation.JsonProperty;

import ch.rasc.openai4j.common.ImageDetail;
import ch.rasc.openai4j.common.ImageFile;

public class ImageFileMessageContent extends MessageContent {

	private final ImageFile imageFile;

	private ImageFileMessageContent(ImageFile imageFile) {
		super("image_file");
		this.imageFile = imageFile;
	}

	public static ImageFileMessageContent of(String fileId) {
		return new ImageFileMessageContent(new ImageFile(fileId, null));
	}

	public static ImageFileMessageContent of(String fileId, ImageDetail detail) {
		return new ImageFileMessageContent(new ImageFile(fileId, detail));
	}

	@JsonProperty("image_file")
	public ImageFile getImageFile() {
		return this.imageFile;
	}

}