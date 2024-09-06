package com.carefusion.carefusion_backend.model.entity;

import com.carefusion.carefusion_backend.model.entity.security.Token;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
    isGetterVisibility = JsonAutoDetect.Visibility.ANY,
    getterVisibility = JsonAutoDetect.Visibility.ANY)
@Entity(name = "User")
@Table(name = "Users")
public class User extends BaseSoftDeleteModel implements UserDetails {

  @Column(name = "uuid", insertable = false, updatable = false, columnDefinition = "BINARY(16)")
  @Generated(GenerationTime.ALWAYS)
  private UUID uuid;

  @Column(name = "first_name")
  private String firstname;
  @Column(name = "last_name")
  private String lastname;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "email_confirmed")
  private boolean emailConfirmed;

  @OneToMany(mappedBy = "user")
  private List<Token> jwtTokens;

  @Column(name = "user_type")
  private String userType;

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

  public List<Token> getJwtTokens() {
    return jwtTokens;
  }

  public void setJwtTokens(List<Token> jwtTokens) {
    this.jwtTokens = jwtTokens;
  }

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

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    User user = (User) o;
    return emailConfirmed == user.emailConfirmed && Objects.equals(uuid, user.uuid)
        && Objects.equals(username, user.username) && Objects.equals(password, user.password)
        && Objects.equals(jwtTokens, user.jwtTokens) && Objects.equals(firstname, user.firstname)
        && Objects.equals(lastname, user.lastname) && Objects.equals(userType, user.userType);
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + Objects.hashCode(uuid);
    result = 31 * result + Objects.hashCode(username);
    result = 31 * result + Objects.hashCode(password);
    result = 31 * result + Boolean.hashCode(emailConfirmed);
    result = 31 * result + Objects.hashCode(jwtTokens);
    result = 31 * result + Objects.hashCode(firstname);
    result = 31 * result + Objects.hashCode(lastname);
    result = 31 * result + Objects.hashCode(userType);
    return result;
  }

  @Override
  public String toString() {
    return "User{" + "uuid=" + uuid + ", firstname='" + firstname + '\'' + ", lastname='" + lastname
        + '\'' + ", username='" + username + '\'' + ", password='" + password + '\''
        + ", emailConfirmed=" + emailConfirmed + ", jwtTokens=" + jwtTokens + ", userType='"
        + userType + '\'' + '}';
  }
}
