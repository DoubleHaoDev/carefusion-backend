package com.carepulse.carepulse_veins.service;

import com.carepulse.carepulse_veins.model.dto.PatientDto;
import java.util.UUID;

public interface PatientService {

  PatientDto getPatient(UUID patientUuid);
}
