package ch.rasc.openai4j.threads.runs.steps;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StepDetailMessageCreation extends StepDetail {

	private final MessageCreation messageCreation;

	@JsonCreator
	public StepDetailMessageCreation(@JsonProperty("type") String type,
			@JsonProperty("message_creation") MessageCreation messageCreation) {
		super(type);
		this.messageCreation = messageCreation;
	}

	public MessageCreation messageCreation() {
		return this.messageCreation;
	}

	class MessageCreation {
		private final String messageId;

		@JsonCreator
		public MessageCreation(@JsonProperty("message_id") String messgageId) {
			this.messageId = messgageId;
		}

		/**
		 * The ID of the message that was created by this run step.
		 */
		public String messageId() {
			return this.messageId;
		}
	}
}
