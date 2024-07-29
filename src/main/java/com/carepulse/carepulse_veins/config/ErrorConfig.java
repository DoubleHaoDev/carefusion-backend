package com.carepulse.carepulse_veins.config;

import com.carepulse.carepulse_veins.exception.ErrorMessage;
import com.carepulse.carepulse_veins.exception.PatientNotFoundException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ControllerAdvice
public class ErrorConfig {

  @ExceptionHandler({PatientNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex) {
    ErrorMessage.Builder builder = new ErrorMessage.Builder();
    ErrorMessage errorMessage =
        builder.setErrorMessage(ex.getMessage()).setStatusCode(HttpStatus.NOT_FOUND.value())
            .setTimestamp(ZonedDateTime.now(ZoneOffset.UTC)).build();

    return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
  }
}
