package com.carepulse.carepulse_veins.rest.impl;

import com.carepulse.carepulse_veins.model.dto.UserDto;
import com.carepulse.carepulse_veins.model.dto.response.LoginResponse;
import com.carepulse.carepulse_veins.model.entity.User;
import com.carepulse.carepulse_veins.rest.AuthenticationApiV1;
import com.carepulse.carepulse_veins.security.service.JwtService;
import com.carepulse.carepulse_veins.service.impl.AuthenticationService;
import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AuthenticationApiV1Impl implements AuthenticationApiV1 {
  private final JwtService jwtService;

  private final AuthenticationService authenticationService;

  @Inject
  public AuthenticationApiV1Impl(JwtService jwtService,
      AuthenticationService authenticationService) {
    this.jwtService = jwtService;
    this.authenticationService = authenticationService;
  }


  public ResponseEntity<User> register(UserDto registerUserDto) {
    User registeredUser = authenticationService.signup(registerUserDto);
    return ResponseEntity.ok(registeredUser);
  }

  public ResponseEntity<LoginResponse> authenticate(UserDto loginUserDto) {
    User authenticatedUser = authenticationService.authenticate(loginUserDto);

    String jwtToken = jwtService.generateToken(authenticatedUser);

    LoginResponse loginResponse = new LoginResponse();
    loginResponse.setToken(jwtToken);
    loginResponse.setExpiresIn(jwtService.getExpirationTime());

    return ResponseEntity.ok(loginResponse);
  }
}
