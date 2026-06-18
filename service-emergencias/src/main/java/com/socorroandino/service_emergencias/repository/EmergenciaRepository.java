package com.socorroandino.service_emergencias.repository;

import com.socorroandino.service_emergencias.model.Emergencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmergenciaRepository extends JpaRepository<Emergencia, Long> {
}