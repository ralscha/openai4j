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

import ch.rasc.openai4j.files.FileObject;

/**
 * Represents an upload object.
 */
public record UploadObject(String id, int bytes, int createdAt, String filename, String purpose, String status, int expiresAt, FileObject file) {

  /**
   * The unique identifier for the upload.
   */
  @Override
  public String id() {
    return this.id;
  }

  /**
   * The number of bytes in the upload.
   */
  @Override
  public int bytes() {
    return this.bytes;
  }

  /**
   * The Unix timestamp (in seconds) for when the upload was created.
   */
  @Override
  public int createdAt() {
    return this.createdAt;
  }

  /**
   * The name of the file being uploaded.
   */
  @Override
  public String filename() {
    return this.filename;
  }

  /**
   * The intended purpose of the uploaded file.
   */
  @Override
  public String purpose() {
    return this.purpose;
  }

  /**
   * The status of the upload.
   */
  @Override
  public String status() {
    return this.status;
  }

  /**
   * The Unix timestamp (in seconds) for when the upload expires.
   */
  @Override
  public int expiresAt() {
    return this.expiresAt;
  }

  /**
   * The file object created after the upload is completed.
   */
  @Override
  public FileObject file() {
    return this.file;
  }
}
