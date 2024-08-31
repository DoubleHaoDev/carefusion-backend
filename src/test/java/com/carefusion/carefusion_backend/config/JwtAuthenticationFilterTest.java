package com.carefusion.carefusion_backend.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.carefusion.carefusion_backend.dao.security.TokenDao;
import com.carefusion.carefusion_backend.model.entity.security.Token;
import com.carefusion.carefusion_backend.service.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

class JwtAuthenticationFilterTest {

  @Mock
  private JwtService jwtService;

  @Mock
  private UserDetailsService userDetailsService;

  @Mock
  private TokenDao tokenDao;

  @InjectMocks
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Mock
  private HttpServletRequest request;

  @Mock
  private HttpServletResponse response;

  @Mock
  private FilterChain filterChain;

  @Mock
  private UserDetails userDetails;

  @Mock
  private SecurityContext securityContext;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    SecurityContextHolder.clearContext();
  }

  @Test
  void test_noAuthHeader() throws ServletException, IOException {
    when(request.getHeader("Authorization")).thenReturn(null);

    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    verify(filterChain).doFilter(request, response);
    assertNull(SecurityContextHolder.getContext().getAuthentication());
  }

  @Test
  void test_invalidAuthHeader() throws ServletException, IOException {
    when(request.getHeader("Authorization")).thenReturn("InvalidHeader");

    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    verify(filterChain).doFilter(request, response);
    assertNull(SecurityContextHolder.getContext().getAuthentication());
  }

  @Test
  void test_validAuthHeader_invalidToken() throws ServletException, IOException {
    when(request.getHeader("Authorization")).thenReturn("Bearer invalidToken");
    when(jwtService.extractUsername("invalidToken")).thenReturn(null);

    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    verify(filterChain).doFilter(request, response);
    assertNull(SecurityContextHolder.getContext().getAuthentication());
  }

  @Test
  void test_validAuthHeader_validToken() throws ServletException, IOException {
    String jwtToken = "validToken";
    String username = "user@example.com";

    Token token = new Token();
    token.setJwtToken(jwtToken);
    token.setRevoked(false);
    token.setExpired(false);

    when(request.getHeader("Authorization")).thenReturn("Bearer " + jwtToken);
    when(jwtService.extractUsername(jwtToken)).thenReturn(username);
    when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
    when(jwtService.isTokenValid(jwtToken, userDetails)).thenReturn(true);
    when(tokenDao.findByJwtToken(jwtToken)).thenReturn(Optional.of(token));

    jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

    verify(filterChain).doFilter(request, response);
    assertNotNull(SecurityContextHolder.getContext().getAuthentication());
    assertTrue(SecurityContextHolder.getContext()
        .getAuthentication() instanceof UsernamePasswordAuthenticationToken);
  }

  @Test
  void test_validAuthHeader_invalidToken_no_security_context()
      throws ServletException, IOException {
    try (MockedStatic<SecurityContextHolder> securityContextHolderMockedStatic =
        mockStatic(SecurityContextHolder.class)) {
      securityContextHolderMockedStatic.when(SecurityContextHolder::getContext)
          .thenReturn(securityContext);
      when(securityContext.getAuthentication()).thenReturn(null);
      String jwtToken = "validToken";
      String username = "user@example.com";

      Token token = new Token();
      token.setJwtToken(jwtToken);
      token.setRevoked(false);
      token.setExpired(false);

      when(request.getHeader("Authorization")).thenReturn("Bearer " + jwtToken);
      when(jwtService.extractUsername(jwtToken)).thenReturn(null);

      when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);
      when(jwtService.isTokenValid(jwtToken, userDetails)).thenReturn(true);
      when(tokenDao.findByJwtToken(jwtToken)).thenReturn(Optional.of(token));

      jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

      verify(filterChain).doFilter(request, response);
      assertNull(SecurityContextHolder.getContext().getAuthentication());
    }
  }
}
