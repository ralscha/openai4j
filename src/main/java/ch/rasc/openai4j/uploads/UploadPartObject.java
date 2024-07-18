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

/**
 * Represents an upload part object.
 */
public record UploadPartObject(String id, int createdAt, String uploadId, String object) {

  /**
   * The unique identifier for the upload part.
   */
  @Override
  public String id() {
    return this.id;
  }

  /**
   * The Unix timestamp (in seconds) for when the upload part was created.
   */
  @Override
  public int createdAt() {
    return this.createdAt;
  }

  /**
   * The ID of the upload object that this part was added to.
   */
  @Override
  public String uploadId() {
    return this.uploadId;
  }

  /**
   * The object type, which is always "upload.part".
   */
  @Override
  public String object() {
    return this.object;
  }
}
