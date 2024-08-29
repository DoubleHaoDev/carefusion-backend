package com.carefusion.carefusion_backend.service.security.impl;

import com.carefusion.carefusion_backend.dao.UserDao;
import com.carefusion.carefusion_backend.dao.security.TokenDao;
import com.carefusion.carefusion_backend.model.dto.UserDto;
import com.carefusion.carefusion_backend.model.dto.response.AuthenticationResponseDto;
import com.carefusion.carefusion_backend.model.entity.User;
import com.carefusion.carefusion_backend.model.entity.security.Token;
import com.carefusion.carefusion_backend.model.entity.security.TokenType;
import com.carefusion.carefusion_backend.service.security.JwtService;
import javax.inject.Inject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class AuthenticationService {
  private final UserDao userRepository;
  private final TokenDao tokenRepository;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @Inject
  public AuthenticationService(UserDao userRepository, TokenDao tokenRepository,
      JwtService jwtService, AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.tokenRepository = tokenRepository;
  }

  public AuthenticationResponseDto signup(UserDto input) {
    User user = new User();
    user.setUsername(input.getUsername());
    user.setPassword(passwordEncoder.encode(input.getPassword()));
    String jwtToken = jwtService.generateToken(user);
    User savedUser = userRepository.save(user);
    saveUserToken(savedUser, jwtToken);
    return new AuthenticationResponseDto(jwtToken);
  }

  public AuthenticationResponseDto authenticate(UserDto input) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));
    User user = userRepository.findByUsername(input.getUsername())
        .orElseThrow(() -> new RuntimeException("User not found"));
    String jwtToken = jwtService.generateToken(user);

    tokenRepository.deleteByUserId(user.getId());
    saveUserToken(user, jwtToken);

    return new AuthenticationResponseDto(jwtToken);
  }

  private void saveUserToken(User user, String jwtToken) {
    Token token = new Token();
    token.setUser(user);
    token.setJwtToken(jwtToken);
    token.setTokenType(TokenType.BEARER);
    token.setExpired(false);
    token.setRevoked(false);
    tokenRepository.save(token);
  }

}
