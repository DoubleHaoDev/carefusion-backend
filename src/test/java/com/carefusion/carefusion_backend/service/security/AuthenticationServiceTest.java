package com.carefusion.carefusion_backend.service.security;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.carefusion.carefusion_backend.dao.UserDao;
import com.carefusion.carefusion_backend.dao.security.TokenDao;
import com.carefusion.carefusion_backend.model.dto.UserDto;
import com.carefusion.carefusion_backend.model.dto.response.AuthenticationResponseDto;
import com.carefusion.carefusion_backend.model.entity.User;
import com.carefusion.carefusion_backend.service.security.impl.AuthenticationService;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

class AuthenticationServiceTest {

  @Mock
  private JwtService jwtService;

  @Mock
  private UserDetailsService userDetailsService;

  @Mock
  private UserDao userDao;

  @Mock
  private TokenDao tokenDao;
  @Mock
  private AuthenticationManager authenticationManager;

  @InjectMocks
  private AuthenticationService authenticationService;

  @Mock
  private User userDetails;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void test_successfulAuthentication() {
    String username = "user@example.com";
    String password = "password";
    Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);

    when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
    when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
    when(jwtService.generateToken(userDetails)).thenReturn("jwtToken");
    when(userDao.findByUsername(username)).thenReturn(Optional.of(userDetails));

    UserDto userDto = new UserDto();
    userDto.setPassword(password);
    userDto.setUsername(username);
    AuthenticationResponseDto authenticationResponseDto =
        authenticationService.authenticate(userDto);

    assertNotNull(authenticationResponseDto);
    assertEquals("jwtToken", authenticationResponseDto.getToken());
  }

  @Test
  void test_authenticationWithInvalidCredentials() {
    String username = "user@example.com";
    String password = "wrongPassword";

    when(authenticationManager.authenticate(any(Authentication.class)))
        .thenThrow(new BadCredentialsException("Invalid credentials"));
    UserDto userDto = new UserDto();
    userDto.setPassword(password);
    userDto.setUsername(username);
    assertThrows(BadCredentialsException.class, () -> {
      authenticationService.authenticate(userDto);
    });
  }
}
