package com.carepulse.carepulse_veins.mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.carepulse.carepulse_veins.model.dto.PatientDto;
import com.carepulse.carepulse_veins.model.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface PatientMapper {

  PatientDto mapPatientToPatientDto(Patient patient);

  Patient mapPatientDtoToPatient(PatientDto patientDto);


}
