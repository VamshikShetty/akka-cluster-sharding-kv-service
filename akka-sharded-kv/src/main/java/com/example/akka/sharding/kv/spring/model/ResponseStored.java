package com.example.akka.sharding.kv.spring.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * ResponseStored
 */

@JsonTypeName("responseStored")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen")
public class ResponseStored {

  @JsonProperty("stored")
  private Boolean stored;

  public ResponseStored stored(Boolean stored) {
    this.stored = stored;
    return this;
  }

  /**
   * Get stored
   * @return stored
  */
  @NotNull 
  @Schema(name = "stored", required = true)
  public Boolean getStored() {
    return stored;
  }

  public void setStored(Boolean stored) {
    this.stored = stored;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResponseStored responseStored = (ResponseStored) o;
    return Objects.equals(this.stored, responseStored.stored);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stored);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ResponseStored {\n");
    sb.append("    stored: ").append(toIndentedString(stored)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

