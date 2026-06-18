package com.socorroandino.service_rescates.repository;

import com.socorroandino.service_rescates.model.Rescatista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RescatistaRepository extends JpaRepository<Rescatista, Long> {
}