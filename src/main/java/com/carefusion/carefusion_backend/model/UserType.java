package com.carefusion.carefusion_backend.model;

public enum UserType {
  PATIENT("patient"), PROVIDER("provider"), ADMIN("admin");

  private String userType;

  UserType(String userType) {
    this.userType = userType;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }
}
