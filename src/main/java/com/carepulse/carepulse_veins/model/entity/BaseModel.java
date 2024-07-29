package com.carepulse.carepulse_veins.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "created_at")
  private ZonedDateTime createdAt;

  @Column(name = "updated_at")
  private ZonedDateTime updatedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public ZonedDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(ZonedDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @PrePersist
  public void preSave() {
    preSaveImpl();
  }

  @PreUpdate
  public void preUpdate() {
    preUpdateImpl();
  }

  protected void preSaveImpl() {
    ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
    if (createdAt == null) {
      createdAt = now;
    }
    preUpdateImpl();
  }

  protected void preUpdateImpl() {
    updatedAt = ZonedDateTime.now(ZoneOffset.UTC);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BaseModel baseModel = (BaseModel) o;
    return Objects.equals(id, baseModel.id) && Objects.equals(createdAt, baseModel.createdAt)
        && Objects.equals(updatedAt, baseModel.updatedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt, updatedAt);
  }

  @Override
  public String toString() {
    return "BaseModel{" + "id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
        + '}';
  }
}
