package com.carefusion.carefusion_backend.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.carefusion.carefusion_backend.exception.UserNotFoundException;
import com.carefusion.carefusion_backend.model.dto.UserDto;
import com.carefusion.carefusion_backend.model.dto.response.AuthenticationResponseDto;
import com.carefusion.carefusion_backend.rest.impl.AuthenticationApiV1Impl;
import com.carefusion.carefusion_backend.service.security.impl.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

class AuthenticationApiV1Test extends AbstractApiTest {

  AuthenticationService authenticationServiceMock;

  @Override
  protected Object setupController() {
    authenticationServiceMock = mock(AuthenticationService.class);
    return new AuthenticationApiV1Impl(authenticationServiceMock);
  }

  @Test
  void test_register_success() throws Exception {
    AuthenticationResponseDto response = new AuthenticationResponseDto("jwtToken");
    when(authenticationServiceMock.authenticate(any(UserDto.class))).thenReturn(response);

    mockMvc.perform(post("/v1/authentication/signup").contentType(MediaType.APPLICATION_JSON)
        .content("{\"username\":\"a@b.com\",\"password\":\"password\"}")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  void test_login_success() throws Exception {
    AuthenticationResponseDto response = new AuthenticationResponseDto("jwtToken");
    when(authenticationServiceMock.authenticate(any(UserDto.class))).thenReturn(response);

    mockMvc.perform(post("/v1/authentication/login").contentType(MediaType.APPLICATION_JSON)
        .content("{\"username\":\"a@b.com\",\"password\":\"password\"}")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  void test_login_user_not_found() throws Exception {
    when(authenticationServiceMock.authenticate(any(UserDto.class)))
        .thenThrow(new UserNotFoundException("Test User"));

    mockMvc.perform(post("/v1/authentication/login").contentType(MediaType.APPLICATION_JSON)
        .content("{\"username\":\"a@b.com\",\"password\":\"password\"}")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
  }

}
