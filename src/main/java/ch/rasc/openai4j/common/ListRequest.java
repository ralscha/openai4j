package ch.rasc.openai4j.common;

import java.util.HashMap;
import java.util.Map;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ch.rasc.openai4j.Nullable;

@Value.Immutable
@Value.Style(visibility = ImplementationVisibility.PACKAGE)
@JsonSerialize(as = ImmutableListRequest.class)
@JsonInclude(Include.NON_EMPTY)
public interface ListRequest {

	/*
	 * A limit on the number of objects to be returned. Limit can range between 1 and 100,
	 * and the default is 20.
	 */
	@Nullable
	Integer limit();

	/*
	 * Sort order by the created_at timestamp of the objects. asc for ascending order and
	 * desc for descending order. Defaults to desc
	 */
	@Nullable
	SortOrder order();

	/*
	 * A cursor for use in pagination. after is an object ID that defines your place in
	 * the list. For instance, if you make a list request and receive 100 objects, ending
	 * with obj_foo, your subsequent call can include after=obj_foo in order to fetch the
	 * next page of the list.
	 */
	@Nullable
	String after();

	/*
	 * A cursor for use in pagination. before is an object ID that defines your place in
	 * the list. For instance, if you make a list request and receive 100 objects, ending
	 * with obj_foo, your subsequent call can include before=obj_foo in order to fetch the
	 * previous page of the list.
	 */
	@Nullable
	String before();

	static Builder builder() {
		return new Builder();
	}

	final class Builder extends ImmutableListRequest.Builder {
	}

	default Map<String, Object> toMap() {
		Map<String, Object> queryParameters = new HashMap<>();
		if (limit() != null) {
			queryParameters.put("limit", limit());
		}
		if (order() != null) {
			queryParameters.put("order", order().value());
		}
		if (after() != null && !after().isBlank()) {
			queryParameters.put("after", after());
		}
		if (before() != null && !before().isBlank()) {
			queryParameters.put("before", before());
		}
		return queryParameters;
	}
}
