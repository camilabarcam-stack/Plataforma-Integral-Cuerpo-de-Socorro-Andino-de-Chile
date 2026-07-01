package com.socorroandino.service_beneficiarios.controller;

import com.socorroandino.service_beneficiarios.model.EntregaDonacion;
import com.socorroandino.service_beneficiarios.service.EntregaDonacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/entregas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EntregaDonacionController {

    private final EntregaDonacionService entregaDonacionService;

    @GetMapping
    public List<EntregaDonacion> listar() {
        return entregaDonacionService.listarTodas();
    }

    @PostMapping
    public EntregaDonacion reportarEntrega(@RequestParam Long idBeneficiario, 
                                            @RequestParam Long idInventario, 
                                            @RequestParam Integer cantidad, 
                                            @RequestParam Long idUsuarioResponsable) {
        return entregaDonacionService.registrarEntrega(idBeneficiario, idInventario, cantidad, idUsuarioResponsable);
    }

    // Manejo de excepciones para que Postman reciba respuestas legibles ante fallas de negocio
    @ExceptionHandler({IllegalArgumentException.class, RuntimeException.class})
    public ResponseEntity<String> capturarErroresDeNegocio(Exception ex) {
        if (ex instanceof IllegalArgumentException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); // 400 Stock Insuficiente
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // 404 Error en microservicio o id vacío
    }
}