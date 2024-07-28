package com.carepulse.carepulse_veins.rest.impl;

import com.carepulse.carepulse_veins.model.dto.PatientDto;
import com.carepulse.carepulse_veins.rest.PatientApiV1;
import com.carepulse.carepulse_veins.service.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import java.util.UUID;

@Controller
public class PatientApiV1Impl implements PatientApiV1 {

  private PatientService patientService;

  @Inject
  public PatientApiV1Impl(PatientService patientService) {
    this.patientService = patientService;
  }

  @Override
  public ResponseEntity<PatientDto> getPatients(UUID uuid) {
    return ResponseEntity.ok(patientService.getPatient(uuid));
  }
}
