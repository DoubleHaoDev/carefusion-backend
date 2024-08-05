package com.carefusion.carefusion_backend.exception;

import com.carefusion.carefusion_backend.model.dto.PatientDto;
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
