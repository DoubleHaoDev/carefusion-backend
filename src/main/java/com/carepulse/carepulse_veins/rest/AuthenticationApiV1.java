package com.carepulse.carepulse_veins.rest;

import com.carepulse.carepulse_veins.model.dto.UserDto;
import com.carepulse.carepulse_veins.model.dto.response.LoginResponse;
import com.carepulse.carepulse_veins.model.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/v1/authentication", produces = "application/json")
public interface AuthenticationApiV1 {

  @PostMapping("/signup")
  ResponseEntity<User> register(@RequestBody UserDto registerUserDto);

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> authenticate(@RequestBody UserDto loginUserDto);
}
