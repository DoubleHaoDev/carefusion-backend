package com.carepulse.carepulse_veins.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.time.ZonedDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseSoftDeleteModel extends BaseModel {
  @Column(name = "deleted_at")
  private ZonedDateTime deletedAt;

  public BaseSoftDeleteModel() {}

  public ZonedDateTime getDeletedAt() {
    return deletedAt;
  }

  public void setDeletedAt(ZonedDateTime deletedAt) {
    this.deletedAt = deletedAt;
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
    BaseSoftDeleteModel that = (BaseSoftDeleteModel) o;
    return Objects.equals(deletedAt, that.deletedAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), deletedAt);
  }

  @Override
  public String toString() {
    return "BaseSoftDeleteModel{" + "deletedAt=" + deletedAt + '}';
  }
}
