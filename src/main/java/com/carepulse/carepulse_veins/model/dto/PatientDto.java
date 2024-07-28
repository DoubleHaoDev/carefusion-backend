package com.carepulse.carepulse_veins.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.annotation.Nullable;
import java.util.UUID;
import java.time.ZonedDateTime;
import org.inferred.freebuilder.FreeBuilder;


@FreeBuilder
@JsonDeserialize(builder = PatientDto.Builder.class)
public interface PatientDto {
  @Nullable
  Long getId();

  @Nullable
  UUID getUuid();

  @Nullable
  Long getPersonId();

  @Nullable
  ZonedDateTime getUpdatedAt();

  @Nullable
  ZonedDateTime getCreatedAt();

  @Nullable
  ZonedDateTime getDeletedAt();

  class Builder extends PatientDto_Builder {

  }

  static Builder builder() {
    return new Builder();
  }

}
