package com.socorroandino.service_emergencias.controller;

import com.socorroandino.service_emergencias.model.Emergencia;
import com.socorroandino.service_emergencias.service.EmergenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/emergencias")
@RequiredArgsConstructor
public class EmergenciaController {

    private final EmergenciaService emergenciaService;

    @GetMapping
    public List<Emergencia> listar() {
        return emergenciaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Emergencia obtener(@PathVariable Long id) {
        return emergenciaService.buscarPorId(id);
    }

    @PostMapping
    public Emergencia reportar(@RequestBody Emergencia emergencia) {
        return emergenciaService.registrarEmergencia(emergencia);
    }

    @PutMapping("/{id}/estado")
    public Emergencia cambiarEstado(@PathVariable Long id, @RequestParam String estado) {
        return emergenciaService.actualizarEstado(id, estado);
    }
}