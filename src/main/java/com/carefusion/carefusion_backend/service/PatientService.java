package com.carefusion.carefusion_backend.service;

import com.carefusion.carefusion_backend.model.dto.PatientDto;
import java.util.UUID;

public interface PatientService {

  PatientDto getPatient(UUID patientUuid);
}
