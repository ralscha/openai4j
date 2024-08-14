package ch.rasc.openai4j.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jackson.JacksonOption;

public class ResponseFormatJsonSchemaService {

  private final SchemaGenerator schemaGenerator;

  public ResponseFormatJsonSchemaService(ObjectMapper objectMapper) {
    JacksonModule module = new JacksonModule(JacksonOption.RESPECT_JSONPROPERTY_REQUIRED);
    SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(
        SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON).with(module);
    SchemaGeneratorConfig config = configBuilder.build();
    this.schemaGenerator = new SchemaGenerator(config);
  }

  public ResponseFormatJsonSchema createResponseFormatJsonSchema(String description, String name, Object schema, Boolean strict) {
    return ResponseFormatJsonSchema.builder()
        .description(description)
        .name(name)
        .schema(schema)
        .strict(strict)
        .build();
  }
}
