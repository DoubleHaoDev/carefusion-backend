package com.carepulse.carepulse_veins;


import com.carepulse.carepulse_veins.dao.PatientDao;
import com.carepulse.carepulse_veins.exception.PatientNotFoundException;
import com.carepulse.carepulse_veins.mapping.PatientMapper;
import com.carepulse.carepulse_veins.mapping.PatientMapperImpl;
import com.carepulse.carepulse_veins.model.dto.PatientDto;
import com.carepulse.carepulse_veins.model.entity.Patient;
import com.carepulse.carepulse_veins.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
  @Mock
  private PatientDao patientDao;
  private Patient patient;
  @Spy
  PatientMapper patientMapper = new PatientMapperImpl();
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
