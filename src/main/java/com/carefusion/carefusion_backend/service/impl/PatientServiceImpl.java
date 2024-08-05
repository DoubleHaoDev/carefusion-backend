package com.carefusion.carefusion_backend.service.impl;

import com.carefusion.carefusion_backend.dao.PatientDao;
import com.carefusion.carefusion_backend.exception.PatientNotFoundException;
import com.carefusion.carefusion_backend.mapping.PatientMapper;
import com.carefusion.carefusion_backend.model.dto.PatientDto;
import com.carefusion.carefusion_backend.model.entity.Patient;
import com.carefusion.carefusion_backend.service.PatientService;
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
    return patientMapper.mapPatientToPatientDto(patient);
  }
}
