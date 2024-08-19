package com.carefusion.carefusion_backend.model.dto.response;

import java.util.UUID;

public class LoginResponse {
  private String token;

  private long expiresIn;

  private UUID userUuid;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public long getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(long expiresIn) {
    this.expiresIn = expiresIn;
  }

  public UUID getUserUuid() {
    return userUuid;
  }

  public void setUserUuid(UUID userUuid) {
    this.userUuid = userUuid;
  }
}
