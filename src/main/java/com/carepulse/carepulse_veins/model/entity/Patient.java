package com.carepulse.carepulse_veins.model.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
    isGetterVisibility = JsonAutoDetect.Visibility.ANY,
    getterVisibility = JsonAutoDetect.Visibility.ANY)
@Entity(name = "Patient")
@Table(name = "Patients")
public class Patient extends BaseSoftDeleteModel {

  @Column(name = "uuid", insertable = false, updatable = false)
  private UUID uuid;

  @Column(name = "person_id")
  private Long personId;

  @JsonProperty("uuid")
  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  @JsonProperty("personId")
  public Long getPersonId() {
    return personId;
  }

  public void setPersonId(Long personId) {
    this.personId = personId;
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
    Patient patient = (Patient) o;
    return Objects.equals(uuid, patient.uuid) && Objects.equals(personId, patient.personId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), uuid, personId);
  }

  @Override
  public String toString() {
    return "Patient{" + "uuid=" + uuid + ", personId=" + personId + '}';
  }
}
