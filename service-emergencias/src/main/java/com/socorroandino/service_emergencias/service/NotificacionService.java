package com.socorroandino.service_emergencias.service;

import com.socorroandino.service_emergencias.model.Notificacion;
import com.socorroandino.service_emergencias.repository.NotificacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    public Notificacion enviarNotificacion(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    public List<Notificacion> listarTodas() {
        return notificacionRepository.findAll();
    }
}