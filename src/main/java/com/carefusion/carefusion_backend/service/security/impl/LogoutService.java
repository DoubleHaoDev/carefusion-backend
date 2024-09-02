package com.carefusion.carefusion_backend.service.security.impl;

import com.carefusion.carefusion_backend.dao.security.TokenDao;
import com.carefusion.carefusion_backend.model.entity.security.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.inject.Inject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
public class LogoutService implements LogoutHandler {

  private final TokenDao tokenDao;

  @Inject
  public LogoutService(TokenDao tokenDao) {
    this.tokenDao = tokenDao;
  }

  @Override
  public void logout(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) {
    String authHeader = request.getHeader("Authorization");
    String jwt;
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      return;
    }
    jwt = authHeader.substring(7);
    Token storedToken = tokenDao.findByJwtToken(jwt).orElse(null);
    if (storedToken != null) {
      storedToken.setExpired(true);
      storedToken.setRevoked(true);
      tokenDao.save(storedToken);
    }
  }
}
