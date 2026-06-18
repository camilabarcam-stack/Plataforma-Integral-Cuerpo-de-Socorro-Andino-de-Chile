package com.socorroandino.service_emergencias.repository;

import com.socorroandino.service_emergencias.model.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {
}