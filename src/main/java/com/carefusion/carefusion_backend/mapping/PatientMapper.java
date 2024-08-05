package com.carefusion.carefusion_backend.mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.carefusion.carefusion_backend.model.dto.PatientDto;
import com.carefusion.carefusion_backend.model.entity.Patient;
import org.mapstruct.Mapper;

@Mapper(componentModel = SPRING)
public interface PatientMapper {

  PatientDto mapPatientToPatientDto(Patient patient);

  Patient mapPatientDtoToPatient(PatientDto patientDto);


}
