package ch.rasc.openai4j.threads.runs.steps;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CodeInterpreterOutputImage extends CodeInterpreterOutput {

	private final File image;

	@JsonCreator
	public CodeInterpreterOutputImage(@JsonProperty("type") String type,
			@JsonProperty("image") File image) {
		super(type);
		this.image = image;
	}

	/**
	 * The image file
	 */
	public File image() {
		return this.image;
	}

	class File {
		private final String fileId;

		@JsonCreator
		public File(@JsonProperty("file_id") String fileId) {
			this.fileId = fileId;
		}

		/**
		 * The file ID of the image.
		 */
		public String fileId() {
			return this.fileId;
		}
	}

}
