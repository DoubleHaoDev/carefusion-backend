package com.carepulse.carepulse_veins.rest;

import com.carepulse.carepulse_veins.model.dto.PatientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;


@RequestMapping(value = "/v1/patient", produces = "application/json")
public interface PatientApiV1 {

  @GetMapping(value = "/uuid/{uuid}")
  ResponseEntity<PatientDto> getPatients(@PathVariable UUID uuid);
}
