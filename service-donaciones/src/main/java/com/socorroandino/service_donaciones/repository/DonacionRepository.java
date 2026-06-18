package com.socorroandino.service_donaciones.repository;

import com.socorroandino.service_donaciones.model.Donacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {
    List<Donacion> findByIdUsuario(Long idUsuario);
}