package ch.rasc.openai4j.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageFile {

	private final String fileId;
	private final ImageDetail detail;

	public ImageFile(@JsonProperty("file_id") String fileId, ImageDetail detail) {
		if (fileId == null || fileId.isEmpty()) {
			throw new IllegalArgumentException("fileId cannot be null");
		}
		this.fileId = fileId;
		this.detail = detail;
	}

	/**
	 * The File ID of the image in the message content. Set purpose="vision" when
	 * uploading the File if you need to later display the file content.
	 */
	@JsonProperty("file_id")
	public String fileId() {
		return this.fileId;
	}

	/**
	 * Specifies the detail level of the image if specified by the user. low uses fewer
	 * tokens, you can opt in to high resolution using high.
	 */
	@JsonProperty
	public ImageDetail detail() {
		return this.detail;
	}
}