package com.socorroandino.service_donaciones.repository;

import com.socorroandino.service_donaciones.model.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
    Comprobante findByIdDonacion(Long idDonacion);
}