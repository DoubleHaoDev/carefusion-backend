package com.carefusion.carefusion_backend.model.entity;

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

  @Column(name = "primary_phone")
  private String primaryPhone;

  @Column(name = "birthdate")
  private String birthdate;

  @Column(name = "gender")
  private String gender;

  @Column(name = "address")
  private Long address;

  @Column(name = "occupation")
  private Long occupation;

  @Column(name = "emergency_contact_name")
  private Long emergencyContactName;

  @Column(name = "family_medical_history")
  private Long familyMedicalHistory;

  @Column(name = "past_medical_history")
  private Long pastMedicalHistory;

  @Column(name = "identification_type")
  private Long identificationType;

  @Column(name = "treatment_concent")
  private Boolean treatmentConcent;

  @Column(name = "disclosure_concent")
  private Boolean disclosureConcent;

  @Column(name = "privacy_concent")
  private Boolean privacyConcent;

  @Column(name = "user_id")
  private Long userId;

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

  public String getPrimaryPhone() {
    return primaryPhone;
  }

  public void setPrimaryPhone(String primaryPhone) {
    this.primaryPhone = primaryPhone;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public Long getAddress() {
    return address;
  }

  public void setAddress(Long address) {
    this.address = address;
  }

  public Long getOccupation() {
    return occupation;
  }

  public void setOccupation(Long occupation) {
    this.occupation = occupation;
  }

  public Long getEmergencyContactName() {
    return emergencyContactName;
  }

  public void setEmergencyContactName(Long emergencyContactName) {
    this.emergencyContactName = emergencyContactName;
  }

  public Long getFamilyMedicalHistory() {
    return familyMedicalHistory;
  }

  public void setFamilyMedicalHistory(Long familyMedicalHistory) {
    this.familyMedicalHistory = familyMedicalHistory;
  }

  public Long getPastMedicalHistory() {
    return pastMedicalHistory;
  }

  public void setPastMedicalHistory(Long pastMedicalHistory) {
    this.pastMedicalHistory = pastMedicalHistory;
  }

  public Long getIdentificationType() {
    return identificationType;
  }

  public void setIdentificationType(Long identificationType) {
    this.identificationType = identificationType;
  }

  public Boolean getTreatmentConcent() {
    return treatmentConcent;
  }

  public void setTreatmentConcent(Boolean treatmentConcent) {
    this.treatmentConcent = treatmentConcent;
  }

  public Boolean getDisclosureConcent() {
    return disclosureConcent;
  }

  public void setDisclosureConcent(Boolean disclosureConcent) {
    this.disclosureConcent = disclosureConcent;
  }

  public Boolean getPrivacyConcent() {
    return privacyConcent;
  }

  public void setPrivacyConcent(Boolean privacyConcent) {
    this.privacyConcent = privacyConcent;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
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
