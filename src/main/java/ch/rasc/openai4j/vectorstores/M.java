package ch.rasc.openai4j.vectorstores;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.rasc.openai4j.vectorstores.files.VectorStoreFile;

public class M {

	public static void main(String[] args)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String j = """
				{
				                "id": "file1",
				                "chunking_strategy": {
				                    "type": "other"
				                }
				            }
				            """;

		VectorStoreFile file = objectMapper.readValue(j, VectorStoreFile.class);
		System.out.println(file.chunkingStrategy());
	}

}
