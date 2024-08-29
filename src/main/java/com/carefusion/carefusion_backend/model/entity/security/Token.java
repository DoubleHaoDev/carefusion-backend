package com.carefusion.carefusion_backend.model.entity.security;

import com.carefusion.carefusion_backend.model.entity.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
    isGetterVisibility = JsonAutoDetect.Visibility.ANY,
    getterVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Table(name = "tokens")
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;
  @Column(name = "token")
  private String jwtToken;
  @Enumerated(EnumType.STRING)
  @Column(name = "token_type")
  private TokenType tokenType;
  @Column(name = "expired")
  private boolean expired;
  @Column(name = "revoked")
  private boolean revoked;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getJwtToken() {
    return jwtToken;
  }

  public void setJwtToken(String jwtToken) {
    this.jwtToken = jwtToken;
  }

  public TokenType getTokenType() {
    return tokenType;
  }

  public void setTokenType(TokenType tokenType) {
    this.tokenType = tokenType;
  }

  public boolean isExpired() {
    return expired;
  }

  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  public boolean isRevoked() {
    return revoked;
  }

  public void setRevoked(boolean revoked) {
    this.revoked = revoked;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Token token1 = (Token) o;
    return expired == token1.expired && revoked == token1.revoked && Objects.equals(id, token1.id)
        && Objects.equals(jwtToken, token1.jwtToken) && tokenType == token1.tokenType
        && Objects.equals(user, token1.user);
  }

  @Override
  public int hashCode() {
    int result = Objects.hashCode(id);
    result = 31 * result + Objects.hashCode(jwtToken);
    result = 31 * result + Objects.hashCode(tokenType);
    result = 31 * result + Boolean.hashCode(expired);
    result = 31 * result + Boolean.hashCode(revoked);
    result = 31 * result + Objects.hashCode(user);
    return result;
  }

  @Override
  public String toString() {
    return "Token{" + "id=" + id + ", jwtToken='" + jwtToken + '\'' + ", tokenType=" + tokenType
        + ", expired=" + expired + ", revoked=" + revoked + ", user=" + user + '}';
  }
}
