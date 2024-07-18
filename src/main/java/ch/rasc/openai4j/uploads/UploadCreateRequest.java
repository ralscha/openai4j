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
package ch.rasc.openai4j.uploads;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class UploadCreateRequest {

  @JsonProperty("filename")
  private final String filename;

  @JsonProperty("purpose")
  private final String purpose;

  @JsonProperty("bytes")
  private final int bytes;

  @JsonProperty("mime_type")
  private final String mimeType;

  private UploadCreateRequest(Builder builder) {
    if (builder.filename == null || builder.filename.isEmpty()) {
      throw new IllegalArgumentException("filename must not be null or empty");
    }
    this.filename = builder.filename;

    if (builder.purpose == null || builder.purpose.isEmpty()) {
      throw new IllegalArgumentException("purpose must not be null or empty");
    }
    this.purpose = builder.purpose;

    if (builder.bytes <= 0) {
      throw new IllegalArgumentException("bytes must be greater than 0");
    }
    this.bytes = builder.bytes;

    if (builder.mimeType == null || builder.mimeType.isEmpty()) {
      throw new IllegalArgumentException("mimeType must not be null or empty");
    }
    this.mimeType = builder.mimeType;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String filename;
    private String purpose;
    private int bytes;
    private String mimeType;

    /**
     * The name of the file to upload.
     */
    public Builder filename(String filename) {
      this.filename = filename;
      return this;
    }

    /**
     * The intended purpose of the uploaded file.
     */
    public Builder purpose(String purpose) {
      this.purpose = purpose;
      return this;
    }

    /**
     * The number of bytes in the file you are uploading.
     */
    public Builder bytes(int bytes) {
      this.bytes = bytes;
      return this;
    }

    /**
     * The MIME type of the file.
     */
    public Builder mimeType(String mimeType) {
      this.mimeType = mimeType;
      return this;
    }

    public UploadCreateRequest build() {
      return new UploadCreateRequest(this);
    }
  }

  public String getFilename() {
    return filename;
  }

  public String getPurpose() {
    return purpose;
  }

  public int getBytes() {
    return bytes;
  }

  public String getMimeType() {
    return mimeType;
  }
}
