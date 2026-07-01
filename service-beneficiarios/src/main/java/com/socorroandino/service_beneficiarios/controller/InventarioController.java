package com.socorroandino.service_beneficiarios.controller;

import com.socorroandino.service_beneficiarios.model.Inventario;
import com.socorroandino.service_beneficiarios.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/inventario")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class InventarioController {

    private final InventarioService inventarioService;

    @PostMapping
    public Inventario cargar(@RequestBody Inventario inventario) {
        return inventarioService.cargarOActualizar(inventario);
    }

    @GetMapping
    public List<Inventario> listar() {
        return inventarioService.listarTodo();
    }
}