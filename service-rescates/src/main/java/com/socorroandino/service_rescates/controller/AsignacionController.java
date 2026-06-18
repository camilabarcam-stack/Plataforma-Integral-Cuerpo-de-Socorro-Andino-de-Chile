package com.socorroandino.service_rescates.controller;

import com.socorroandino.service_rescates.model.Asignacion;
import com.socorroandino.service_rescates.service.AsignacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/asignaciones")
@RequiredArgsConstructor
public class AsignacionController {

    private final AsignacionService service;

    @GetMapping
    public List<Asignacion> listar() {
        return service.listarTodas();
    }

    @PostMapping
    public Asignacion asignar(@RequestBody Asignacion asignacion) {
        return service.crearAsignacion(asignacion);
    }
}