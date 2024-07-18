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
