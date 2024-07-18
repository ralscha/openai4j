package ch.rasc.openai4j.uploads;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface UploadsClient {

  @RequestLine("POST /uploads")
  @Headers("Content-Type: application/json")
  UploadObject createUpload(UploadCreateRequest request);

  @RequestLine("POST /uploads/{upload_id}/part")
  @Headers("Content-Type: application/json")
  UploadPartObject addPart(@Param("upload_id") String uploadId, UploadPartRequest request);

  @RequestLine("POST /uploads/{upload_id}/complete")
  @Headers("Content-Type: application/json")
  UploadObject completeUpload(@Param("upload_id") String uploadId, UploadCompleteRequest request);

  @RequestLine("POST /uploads/{upload_id}/cancel")
  @Headers("Content-Type: application/json")
  UploadObject cancelUpload(@Param("upload_id") String uploadId);
}
