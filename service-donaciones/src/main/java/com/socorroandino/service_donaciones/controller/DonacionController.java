package com.socorroandino.service_donaciones.controller;

import com.socorroandino.service_donaciones.model.Donacion;
import com.socorroandino.service_donaciones.service.DonacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/donaciones")
@RequiredArgsConstructor
public class DonacionController {

    private final DonacionService donacionService;

    @GetMapping
    public List<Donacion> listar() {
        return donacionService.listarTodas();
    }

    @PostMapping
    public Donacion registrar(@RequestBody Donacion donacion) {
        return donacionService.procesarDonacion(donacion);
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Donacion> listarPorUsuario(@PathVariable Long idUsuario) {
        return donacionService.listarPorUsuario(idUsuario);
    }
}