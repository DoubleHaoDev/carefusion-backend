package com.carefusion.carefusion_backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

import com.carefusion.carefusion_backend.dao.PatientDao;
import com.carefusion.carefusion_backend.exception.PatientNotFoundException;
import com.carefusion.carefusion_backend.mapping.PatientMapper;
import com.carefusion.carefusion_backend.mapping.PatientMapperImpl;
import com.carefusion.carefusion_backend.model.dto.PatientDto;
import com.carefusion.carefusion_backend.model.entity.Patient;
import com.carefusion.carefusion_backend.service.impl.PatientServiceImpl;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

  @Spy
  PatientMapper patientMapper = new PatientMapperImpl();
  @Mock
  private PatientDao patientDao;
  private Patient patient;
  @InjectMocks
  private PatientServiceImpl patientService;
  private UUID patientUuid;

  @BeforeEach
  void setUp() {
    patientUuid = UUID.randomUUID();
  }

  @Test
  void getPatient_success() {
    patient = new Patient();
    patient.setUuid(patientUuid);
    when(patientDao.findByUuid(patientUuid)).thenReturn(Optional.of(patient));
    PatientDto patientDtoResult = patientService.getPatient(patientUuid);
    assertEquals(patientUuid, patientDtoResult.getUuid());
  }

  @Test
  void getPatient_not_found() {
    when(patientDao.findByUuid(patientUuid)).thenReturn(Optional.empty());

    assertThrowsExactly(PatientNotFoundException.class,
        () -> patientService.getPatient(patientUuid));
  }
}
