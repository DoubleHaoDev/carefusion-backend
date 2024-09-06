package com.carefusion.carefusion_backend.model.dto;

import jakarta.validation.constraints.NotNull;

public class UserDto {
  @NotNull(message = "Username cannot be null")
  private String username;
  @NotNull(message = "Password cannot be null")
  private String password;
  @NotNull(message = "First name cannot be null")
  private String firstname;
  @NotNull(message = "Last name cannot be null")
  private String lastname;

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
