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
package ch.rasc.openai4j.moderations;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableModerations.class)
public interface Moderations {

	/**
	 * Whether the content violates
	 * <a href="https://platform.openai.com/policies/usage-policies">OpenAI's usage
	 * policies</a>.
	 */
	boolean flagged();

	/**
	 * A list of the categories, and whether they are flagged or not.
	 */
	ModerationsCategories categories();

	/**
	 * A list of the categories along with their scores as predicted by the model.
	 */
	@JsonProperty("category_scores")
	ModerationsCategoryScores categoryScores();

}
