package com.carefusion.carefusion_backend.rest.impl;

import com.carefusion.carefusion_backend.model.dto.UserDto;
import com.carefusion.carefusion_backend.model.dto.response.AuthenticationResponseDto;
import com.carefusion.carefusion_backend.rest.AuthenticationApiV1;
import com.carefusion.carefusion_backend.service.security.impl.AuthenticationService;
import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AuthenticationApiV1Impl implements AuthenticationApiV1 {

  private final AuthenticationService authenticationService;

  @Inject
  public AuthenticationApiV1Impl(AuthenticationService authenticationService) {
    this.authenticationService = authenticationService;
  }

  public ResponseEntity<AuthenticationResponseDto> register(UserDto registerUserDto) {
    AuthenticationResponseDto registeredResponse = authenticationService.signup(registerUserDto);
    return ResponseEntity.ok(registeredResponse);
  }

  public ResponseEntity<AuthenticationResponseDto> authenticate(UserDto loginUserDto) {
    AuthenticationResponseDto response = authenticationService.authenticate(loginUserDto);
    return ResponseEntity.ok(response);
  }
}
