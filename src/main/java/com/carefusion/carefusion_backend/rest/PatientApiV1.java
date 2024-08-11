package com.carefusion.carefusion_backend.rest;

import com.carefusion.carefusion_backend.model.dto.PatientDto;
import io.swagger.v3.oas.annotations.Operation;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping(value = "/v1/patient", produces = "application/json")
public interface PatientApiV1 {
  @Operation(summary = "Get book store name | Cookie Example")
  @GetMapping(value = "/uuid/{uuid}")
  ResponseEntity<PatientDto> getPatients(@PathVariable UUID uuid);
}
