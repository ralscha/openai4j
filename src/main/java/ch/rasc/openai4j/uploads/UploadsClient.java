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
