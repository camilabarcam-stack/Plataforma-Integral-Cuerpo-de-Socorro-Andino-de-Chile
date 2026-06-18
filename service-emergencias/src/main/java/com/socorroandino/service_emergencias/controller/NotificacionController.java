package com.socorroandino.service_emergencias.controller;

import com.socorroandino.service_emergencias.model.Notificacion;
import com.socorroandino.service_emergencias.service.NotificacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@RequiredArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @PostMapping
    public Notificacion enviar(@RequestBody Notificacion notificacion) {
        return notificacionService.enviarNotificacion(notificacion);
    }

    @GetMapping
    public List<Notificacion> listar() {
        return notificacionService.listarTodas();
    }
}