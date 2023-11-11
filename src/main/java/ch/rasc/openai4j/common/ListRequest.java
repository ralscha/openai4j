/*
 * Copyright the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

	static Builder builder() {
		return new Builder();
	}

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

	final class Builder extends ImmutableListRequest.Builder {
	}
}
