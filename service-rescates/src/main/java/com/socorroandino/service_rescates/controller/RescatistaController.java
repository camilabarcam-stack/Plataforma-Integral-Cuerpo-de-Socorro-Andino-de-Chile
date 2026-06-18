package com.socorroandino.service_rescates.controller;

import com.socorroandino.service_rescates.model.Rescatista;
import com.socorroandino.service_rescates.service.RescatistaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rescatistas")
@RequiredArgsConstructor
public class RescatistaController {

    private final RescatistaService service;

    @GetMapping
    public List<Rescatista> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Rescatista crear(@RequestBody Rescatista rescatista) {
        return service.guardarRescatista(rescatista);
    }
}