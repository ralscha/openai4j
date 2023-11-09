package ch.rasc.openai4j.threads.messages;

import java.util.List;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.files.FileObject;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableThreadMessagesResponse.class)
public interface ThreadMessagesResponse {

	String object();

	List<FileObject> data();

	@JsonProperty("has_more")
	boolean hasMore();

	@JsonProperty("first_id")
	String firstId();

	@JsonProperty("last_id")
	String lastId();
}
