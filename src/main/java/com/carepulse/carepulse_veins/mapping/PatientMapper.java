package com.carepulse.carepulse_veins.mapping;

import com.carepulse.carepulse_veins.model.entity.Patient;
import com.carepulse.carepulse_veins.model.dto.PatientDto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PatientMapper {

  PatientDto mapPatientToPatientDto(Patient patient);



}
