package com.carefusion.carefusion_backend.config;

import com.carefusion.carefusion_backend.dao.security.TokenDao;
import com.carefusion.carefusion_backend.service.security.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private static final String HEADER_STARTS_WITH = "Bearer ";
  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;
  private final TokenDao tokenDao;

  public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService,
      TokenDao tokenDao) {
    this.jwtService = jwtService;
    this.userDetailsService = userDetailsService;
    this.tokenDao = tokenDao;
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
      throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith(HEADER_STARTS_WITH)) {
      filterChain.doFilter(request, response);
      return;
    }

    final String jwt = authHeader.substring(HEADER_STARTS_WITH.length());
    final String userEmail = jwtService.extractUsername(jwt);

    if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
      boolean isTokenValid = tokenDao.findByJwtToken(jwt)
          .map(token -> !token.isExpired() && !token.isRevoked()).orElse(false);
      if (jwtService.isTokenValid(jwt, userDetails) && isTokenValid) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }
}
