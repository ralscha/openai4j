package ch.rasc.openai4j.file;

import java.util.List;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableFilesResponse.class)
public interface FilesResponse {

	String object();

	List<FileObject> data();

	@JsonProperty("has_more")
	boolean hasMore();
}
