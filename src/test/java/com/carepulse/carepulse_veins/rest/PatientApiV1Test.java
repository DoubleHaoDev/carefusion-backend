package com.carepulse.carepulse_veins.rest;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.carepulse.carepulse_veins.exception.PatientNotFoundException;
import com.carepulse.carepulse_veins.model.dto.PatientDto;
import com.carepulse.carepulse_veins.rest.impl.PatientApiV1Impl;
import com.carepulse.carepulse_veins.service.PatientService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.ResultMatcher;

class PatientApiV1Test extends AbstractApiTest {

  private final Long personId = 1L;
  private final Long patientId = 2L;
  private UUID patientUuid;
  private PatientDto patientDto;
  private PatientService patientService;

  @BeforeEach
  void setUp() {
    patientUuid = UUID.randomUUID();

    patientDto =
        PatientDto.builder().setUuid(patientUuid).setPersonId(personId).setId(patientId).build();
  }

  @Override
  protected Object setupController() {
    patientService = mock(PatientService.class);
    return new PatientApiV1Impl(patientService);
  }

  @Test
  void getPatients_success() throws Exception {
    when(patientService.getPatient(patientDto.getUuid())).thenReturn(patientDto);

    mockMvc.perform(get("/v1/patient/uuid/{uuid}", patientDto.getUuid())).andExpect(status().isOk())
        .andExpectAll(matchPatientDto());
  }

  @Test
  void getPatients_patient_not_found() throws Exception {
    when(patientService.getPatient(patientDto.getUuid()))
        .thenThrow(new PatientNotFoundException(patientDto.getUuid()));

    mockMvc.perform(get("/v1/patient/uuid/{uuid}", patientDto.getUuid()))
        .andExpect(status().isNotFound());
  }

  private ResultMatcher[] matchPatientDto() {
    return new ResultMatcher[] {jsonPath("$.uuid").value(notNullValue()),
        jsonPath("$.id").value(2L)};
  }

}
