package ch.rasc.openai4j.common;

import java.util.List;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableListResponse.class)
public interface ListResponse<T> {

	String object();

	List<T> data();

	@JsonProperty("has_more")
	boolean hasMore();

	@Nullable
	@JsonProperty("first_id")
	String firstId();

	@Nullable
	@JsonProperty("last_id")
	String lastId();
}
