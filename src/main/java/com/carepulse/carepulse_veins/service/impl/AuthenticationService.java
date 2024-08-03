package com.carepulse.carepulse_veins.service.impl;

import com.carepulse.carepulse_veins.dao.UserDao;
import com.carepulse.carepulse_veins.model.dto.UserDto;
import com.carepulse.carepulse_veins.model.entity.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
  private final UserDao userRepository;

  private final PasswordEncoder passwordEncoder;

  private final AuthenticationManager authenticationManager;

  public AuthenticationService(UserDao userRepository, AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder) {
    this.authenticationManager = authenticationManager;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User signup(UserDto input) {
    User user = new User();
    user.setUsername(input.getUsername());
    user.setPassword(passwordEncoder.encode(input.getPassword()));

    return userRepository.save(user);
  }

  public User authenticate(UserDto input) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(input.getUsername(), input.getPassword()));

    return userRepository.findByUsername(input.getUsername()).orElseThrow();
  }
}
