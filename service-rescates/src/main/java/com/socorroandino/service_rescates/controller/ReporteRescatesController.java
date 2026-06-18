package com.socorroandino.service_rescates.controller;

import com.socorroandino.service_rescates.model.ReporteRescates;
import com.socorroandino.service_rescates.service.ReporteRescatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes-rescate")
@RequiredArgsConstructor
public class ReporteRescatesController {

    private final ReporteRescatesService service;

    @GetMapping
    public List<ReporteRescates> historial() {
        return service.listarHistorial();
    }

    @PostMapping
    public ReporteRescates crear(@RequestBody ReporteRescates reporte) {
        return service.guardarReporte(reporte);
    }
}