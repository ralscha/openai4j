package ch.rasc.openai4j.common;

import java.util.Map;

public class ResponseFormatJsonSchema {
  private final String description;
  private final String name;
  private final Map<String, Object> schema;
  private final Boolean strict;

  private ResponseFormatJsonSchema(Builder builder) {
    this.description = builder.description;
    this.name = builder.name;
    this.schema = builder.schema;
    this.strict = builder.strict;
  }

  public String getDescription() {
    return description;
  }

  public String getName() {
    return name;
  }

  public Map<String, Object> getSchema() {
    return schema;
  }

  public Boolean getStrict() {
    return strict;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private String description;
    private String name;
    private Map<String, Object> schema;
    private Boolean strict;

    private Builder() {
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder schema(Map<String, Object> schema) {
      this.schema = schema;
      return this;
    }

    public Builder strict(Boolean strict) {
      this.strict = strict;
      return this;
    }

    public ResponseFormatJsonSchema build() {
      return new ResponseFormatJsonSchema(this);
    }
  }
}
