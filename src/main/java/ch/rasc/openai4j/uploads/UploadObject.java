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
