package ch.rasc.openai4j.uploads;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UploadPartRequest {

  private final String data;

  private UploadPartRequest(Builder builder) {
    if (builder.data == null || builder.data.isEmpty()) {
      throw new IllegalArgumentException("data must not be null or empty");
    }
    this.data = builder.data;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String data;

    /**
     * The chunk of bytes for this Part.
     */
    public Builder data(String data) {
      this.data = data;
      return this;
    }

    public UploadPartRequest build() {
      return new UploadPartRequest(this);
    }
  }

  public String getData() {
    return data;
  }
}
