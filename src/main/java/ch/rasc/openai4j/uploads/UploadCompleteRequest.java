package ch.rasc.openai4j.uploads;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UploadCompleteRequest {

  @JsonProperty("part_ids")
  private final List<String> partIds;
  private final String md5;

  private UploadCompleteRequest(Builder builder) {
    if (builder.partIds == null || builder.partIds.isEmpty()) {
      throw new IllegalArgumentException("partIds must not be null or empty");
    }
    this.partIds = builder.partIds;
    this.md5 = builder.md5;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private List<String> partIds;
    private String md5;

    /**
     * The ordered list of Part IDs.
     */
    public Builder partIds(List<String> partIds) {
      this.partIds = partIds;
      return this;
    }

    /**
     * The optional md5 checksum for the file contents to verify if the bytes uploaded matches what you expect.
     */
    public Builder md5(String md5) {
      this.md5 = md5;
      return this;
    }

    public UploadCompleteRequest build() {
      return new UploadCompleteRequest(this);
    }
  }

  public List<String> getPartIds() {
    return partIds;
  }

  public String getMd5() {
    return md5;
  }
}
