package com.carepulse.carepulse_veins.service.impl;

import com.carepulse.carepulse_veins.dao.PatientDao;
import com.carepulse.carepulse_veins.exception.PatientNotFoundException;
import com.carepulse.carepulse_veins.mapping.PatientMapper;
import com.carepulse.carepulse_veins.model.dto.PatientDto;
import com.carepulse.carepulse_veins.model.entity.Patient;
import com.carepulse.carepulse_veins.service.PatientService;
import java.util.UUID;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PatientServiceImpl implements PatientService {

  private final PatientDao patientDao;
  private final PatientMapper patientMapper;

  @Inject
  public PatientServiceImpl(PatientDao patientDao, PatientMapper patientMapper) {
    this.patientDao = patientDao;
    this.patientMapper = patientMapper;
  }

  @Override
  public PatientDto getPatient(UUID patientUuid) {
    Patient patient = patientDao.findByUuid(patientUuid)
        .orElseThrow(() -> new PatientNotFoundException(patientUuid));
    PatientDto patientDto = patientMapper.mapPatientToPatientDto(patient);
    return patientDto;
  }
}
