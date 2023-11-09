package ch.rasc.openai4j;

import java.io.FileOutputStream;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

import ch.rasc.openai4j.files.FileUploadRequest;
import ch.rasc.openai4j.files.FilesClient;
import ch.rasc.openai4j.files.Purpose;
import feign.Feign;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

public class FileExample {
	public static void main(String[] args) {
		String token = Util.getApiKey();

		ObjectMapper om = new ObjectMapper();
		om.registerModule(new Jdk8Module());

		var client = Feign.builder().decoder(new JacksonDecoder(om))
				.encoder(new FormEncoder(new JacksonEncoder(om)))
				.requestInterceptor(new AuthorizationRequestInterceptor(token))
				.target(FilesClient.class, "https://api.openai.com/v1");

		var files = client.files();
		System.out.println(files);
		for (var response : files.data()) {
			System.out.println(response);

			var dstatus = client.delete(response.id());
			System.out.println(dstatus);
		}

		System.out.println();

		var filesWithAPurpose = client.files(Purpose.FINE_TUNE.toValue());
		System.out.println(filesWithAPurpose);

		var p = Paths.get("./image1.png");
		var response = client.upload(
				FileUploadRequest.builder().file(p).purpose(Purpose.ASSISTANTS).build());
		System.out.println(response);

		System.out.println();
		var fileInfo = client.file(response.id());
		System.out.println(fileInfo);

		System.out.println();
		files = client.files();
		for (var f : files.data()) {
			System.out.println(f);
		}

		try (var resp = client.content(response.id());
				FileOutputStream fos = new FileOutputStream("image1_copy.png");
				var body = resp.body();
				var is = body.asInputStream()) {
			is.transferTo(fos);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		files = client.files();
		for (var r : files.data()) {
			client.delete(r.id());
		}

	}

}
