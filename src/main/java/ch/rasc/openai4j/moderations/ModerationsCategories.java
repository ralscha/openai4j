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
@JsonDeserialize(as = ImmutableModerationsCategories.class)
public interface ModerationsCategories {

	/**
	 * Content that expresses, incites, or promotes hate based on race, gender, ethnicity,
	 * religion, nationality, sexual orientation, disability status, or caste. Hateful
	 * content aimed at non-protected groups (e.g., chess players) is harrassment.
	 */
	boolean hate();

	/**
	 * Hateful content that also includes violence or serious harm towards the targeted
	 * group based on race, gender, ethnicity, religion, nationality, sexual orientation,
	 * disability status, or caste.
	 */
	@JsonProperty("hate/threatening")
	boolean hateThreatening();

	/**
	 * Content that expresses, incites, or promotes harassing language towards any target.
	 */
	boolean harassment();

	/**
	 * Harassment content that also includes violence or serious harm towards any target.
	 */
	@JsonProperty("harassment/threatening")
	boolean harassmentThreatening();

	/**
	 * Content that promotes, encourages, or depicts acts of self-harm, such as suicide,
	 * cutting, and eating disorders
	 */
	@JsonProperty("self-harm")
	boolean selfHarm();

	/**
	 * Content where the speaker expresses that they are engaging or intend to engage in
	 * acts of self-harm, such as suicide, cutting, and eating disorders.
	 */
	@JsonProperty("self-harm/intent")
	boolean selfHarmIntent();

	/**
	 * Content that encourages performing acts of self-harm, such as suicide, cutting, and
	 * eating disorders, or that gives instructions or advice on how to commit such acts.
	 */
	@JsonProperty("self-harm/instructions")
	boolean selfHarmInstructions();

	/**
	 * Content meant to arouse sexual excitement, such as the description of sexual
	 * activity, or that promotes sexual services (excluding sex education and wellness).
	 */
	boolean sexual();

	/**
	 * Sexual content that includes an individual who is under 18 years old.
	 */
	@JsonProperty("sexual/minors")
	boolean sexualMinors();

	/**
	 * Content that depicts death, violence, or physical injury.
	 */
	boolean violence();

	/**
	 * Content that depicts death, violence, or physical injury in graphic detail.
	 */
	@JsonProperty("violence/graphic")
	boolean violenceGraphic();

}
