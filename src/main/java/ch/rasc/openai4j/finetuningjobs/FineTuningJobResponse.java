package ch.rasc.openai4j.finetuningjobs;

import java.util.List;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.ImplementationVisibility;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@Value.Immutable(builder = false)
@Value.Style(visibility = ImplementationVisibility.PACKAGE, allParameters = true)
@JsonDeserialize(as = ImmutableFineTuningJobResponse.class)
public interface FineTuningJobResponse {
	String object();

	List<FineTuningJobObject> data();

	@JsonProperty("has_more")
	boolean hasMore();
}
