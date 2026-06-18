package com.socorroandino.service_donaciones.controller;

import com.socorroandino.service_donaciones.model.Comprobante;
import com.socorroandino.service_donaciones.service.ComprobanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/comprobantes")
@RequiredArgsConstructor
public class ComprobanteController {

    private final ComprobanteService service;

    @GetMapping
    public List<Comprobante> listar() {
        return service.listarTodos();
    }

    @GetMapping("/donacion/{idDonacion}")
    public Comprobante obtenerPorDonacion(@PathVariable Long idDonacion) {
        return service.buscarPorDonacion(idDonacion);
    }
}