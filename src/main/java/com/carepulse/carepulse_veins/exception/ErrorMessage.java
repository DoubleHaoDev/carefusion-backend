package com.carepulse.carepulse_veins.exception;


import com.carepulse.carepulse_veins.model.dto.PatientDto;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.inferred.freebuilder.FreeBuilder;

import java.time.ZonedDateTime;

@FreeBuilder
@JsonDeserialize(builder = ErrorMessage.Builder.class)
public interface ErrorMessage {

  String getErrorMessage();

  int getStatusCode();

  ZonedDateTime getTimestamp();

  class Builder extends ErrorMessage_Builder {

  }

  static PatientDto.Builder builder() {
    return new PatientDto.Builder();
  }
}
