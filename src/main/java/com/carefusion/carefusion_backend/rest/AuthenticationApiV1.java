package com.carefusion.carefusion_backend.rest;

import com.carefusion.carefusion_backend.model.dto.UserDto;
import com.carefusion.carefusion_backend.model.dto.response.AuthenticationResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/v1/authentication", produces = "application/json")
public interface AuthenticationApiV1 {

  @PostMapping("/signup")
  ResponseEntity<AuthenticationResponseDto> register(@Valid @RequestBody UserDto registerUserDto);

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponseDto> authenticate(@RequestBody UserDto loginUserDto);
}
