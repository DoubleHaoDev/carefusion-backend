package com.carefusion.carefusion_backend.rest.impl;

import com.carefusion.carefusion_backend.model.dto.PatientDto;
import com.carefusion.carefusion_backend.rest.PatientApiV1;
import com.carefusion.carefusion_backend.service.PatientService;
import java.util.UUID;
import javax.inject.Inject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientApiV1Impl implements PatientApiV1 {

  private final PatientService patientService;

  @Inject
  public PatientApiV1Impl(PatientService patientService) {
    this.patientService = patientService;
  }

  @Override
  public ResponseEntity<PatientDto> getPatients(UUID uuid) {
    return ResponseEntity.ok(patientService.getPatient(uuid));
  }
}
