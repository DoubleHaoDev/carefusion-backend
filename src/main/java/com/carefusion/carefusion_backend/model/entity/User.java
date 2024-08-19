package com.carefusion.carefusion_backend.model.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
    isGetterVisibility = JsonAutoDetect.Visibility.ANY,
    getterVisibility = JsonAutoDetect.Visibility.ANY)
@Entity(name = "User")
@Table(name = "Users")
public class User extends BaseSoftDeleteModel implements UserDetails {

  @Column(name = "uuid", insertable = false, updatable = false)
  private UUID uuid;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "email_confirmed")
  private boolean emailConfirmed;

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  @Override
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

  public boolean isEmailConfirmed() {
    return emailConfirmed;
  }

  public void setEmailConfirmed(boolean emailConfirmed) {
    this.emailConfirmed = emailConfirmed;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }
}
