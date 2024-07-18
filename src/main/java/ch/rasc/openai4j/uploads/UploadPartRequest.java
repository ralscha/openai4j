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
