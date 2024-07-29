package com.carepulse.carepulse_veins.exception;

import java.util.UUID;

public class PatientNotFoundException extends RuntimeException {

  private static final Long serialVersionUID = 1L;
  private final UUID patientUuid;

  public PatientNotFoundException(UUID patientUuid) {
    super("Patient for patient uuid: " + patientUuid);
    this.patientUuid = patientUuid;
  }

  public UUID getPatientUuid() {
    return patientUuid;
  }
}
