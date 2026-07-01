package com.socorroandino.service_beneficiarios.controller;

import com.socorroandino.service_beneficiarios.model.Beneficiario;
import com.socorroandino.service_beneficiarios.model.Inventario;
import com.socorroandino.service_beneficiarios.model.EntregaDonacion;
import com.socorroandino.service_beneficiarios.service.BeneficiarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/beneficiarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BeneficiarioController {

    private final BeneficiarioService beneficiarioService;

    // --- ENDPOINTS DE BENEFICIARIOS ---

    @GetMapping
    public List<Beneficiario> listar() {
        return beneficiarioService.listarBeneficiarios();
    }

    @PostMapping
    public Beneficiario registrar(@RequestBody Beneficiario beneficiario) {
        return beneficiarioService.guardarBeneficiario(beneficiario);
    }

    // --- ENDPOINTS DE BODEGA E INVENTARIO ---

    @GetMapping("/inventario")
    public List<Inventario> verInventario() {
        return beneficiarioService.listarInventario();
    }

    @PostMapping("/inventario")
    public Inventario cargarInventario(@RequestBody Inventario inventario) {
        return beneficiarioService.guardarOActualizarInventario(inventario);
    }

    // --- ENDPOINTS DE TRAZABILIDAD Y AYUDAS ---

    @GetMapping("/historial")
    public List<EntregaDonacion> verHistorial() {
        return beneficiarioService.listarHistorialEntregas();
    }

    @PostMapping("/entrega")
    public EntregaDonacion registrarEntrega(@RequestParam Long idBeneficiario, 
                                            @RequestParam Long idInventario, 
                                            @RequestParam Integer cantidad, 
                                            @RequestParam Long idUsuarioResponsable) {
        return beneficiarioService.registrarEntrega(idBeneficiario, idInventario, cantidad, idUsuarioResponsable);
    }

    // --- MANEJO CENTRALIZADO DE EXCEPCIONES PARA POSTMAN ---
    @ExceptionHandler({IllegalArgumentException.class, RuntimeException.class})
    public ResponseEntity<String> capturarErroresDeNegocio(Exception ex) {
        if (ex instanceof IllegalArgumentException) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST); // 400 Stock Insuficiente
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND); // 404 Error en microservicio o id vacío
    }
}