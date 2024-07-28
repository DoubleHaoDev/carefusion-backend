package com.carepulse.carepulse_veins.dao;

import com.carepulse.carepulse_veins.model.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;
import java.util.UUID;

@Repository
public interface PatientDao extends JpaRepository<Patient, Long> {
  Optional<Patient> findByUuid(UUID uuid);

}
