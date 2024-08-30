package com.carefusion.carefusion_backend.service.security;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.carefusion.carefusion_backend.dao.security.TokenDao;
import com.carefusion.carefusion_backend.model.entity.security.Token;
import com.carefusion.carefusion_backend.service.security.impl.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

class LogoutServiceTest {

  @Mock
  private TokenDao tokenRepository;

  @InjectMocks
  private LogoutService logoutService;
  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private Authentication authentication;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void test_logout_success() {
    String token = "validToken";
    Token tokenEntity = new Token();
    tokenEntity.setJwtToken(token);
    tokenEntity.setExpired(false);
    tokenEntity.setRevoked(false);

    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(tokenRepository.findByJwtToken(token)).thenReturn(java.util.Optional.of(tokenEntity));

    logoutService.logout(request, response, authentication);

    assertTrue(tokenEntity.isExpired());
    assertTrue(tokenEntity.isRevoked());
    verify(tokenRepository).save(tokenEntity);
  }

  @Test
  void test_logout_invalidToken() {
    String token = "invalidToken";

    when(request.getHeader("Authorization")).thenReturn("Bearer " + token);
    when(tokenRepository.findByJwtToken(token)).thenReturn(java.util.Optional.empty());

    verify(tokenRepository, never()).save(any(Token.class));
  }
}
