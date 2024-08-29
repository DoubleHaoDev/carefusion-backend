package com.carefusion.carefusion_backend.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.carefusion.carefusion_backend.model.entity.User;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;


public class JwtServiceTest {

  private User userDetails;

  @InjectMocks
  private JwtService jwtService;

  private String token;

  @BeforeEach
  public void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    Field secretKeyField = JwtService.class.getDeclaredField("secretKey");
    secretKeyField.setAccessible(true);
    secretKeyField.set(jwtService,
        "28441fa798e44c8e54e7f069f373313b9a50bbf0ddfea22be22fe755cc14c5a3");

    Field jwtExpirationField = JwtService.class.getDeclaredField("jwtExpiration");
    jwtExpirationField.setAccessible(true);
    jwtExpirationField.set(jwtService, 3600000L);
    userDetails = new User();
    userDetails.setUsername("user@example.com");
    token = jwtService.generateToken(userDetails);
  }

  @Test
  public void test_generateToken() {
    when(userDetails.getUsername()).thenReturn("user@example.com");
    String generatedToken = jwtService.generateToken(userDetails);
    assertNotNull(generatedToken);
  }

  @Test
  public void test_extractUsername() {
    String username = jwtService.extractUsername(token);
    assertEquals("user@example.com", username);
  }

  @Test
  public void test_isTokenValid() {
    boolean isValid = jwtService.isTokenValid(token, userDetails);
    assertTrue(isValid);
  }
}
