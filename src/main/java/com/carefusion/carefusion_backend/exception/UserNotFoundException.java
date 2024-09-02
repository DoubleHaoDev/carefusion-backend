package com.carefusion.carefusion_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

  private static final Long serialVersionUID = 1L;

  public UserNotFoundException(String username) {
    super("User name: " + username + " not found");

  }
}
