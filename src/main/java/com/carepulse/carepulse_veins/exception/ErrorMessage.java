package com.carepulse.carepulse_veins.exception;

import com.carepulse.carepulse_veins.model.dto.PatientDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.ZonedDateTime;
import org.inferred.freebuilder.FreeBuilder;

@FreeBuilder
@JsonDeserialize(builder = ErrorMessage.Builder.class)
public interface ErrorMessage {

  static PatientDto.Builder builder() {
    return new PatientDto.Builder();
  }

  String getErrorMessage();

  int getStatusCode();

  ZonedDateTime getTimestamp();

  class Builder extends ErrorMessage_Builder {

  }
}
