package com.socorroandino.service_beneficiarios.service;

import com.socorroandino.service_beneficiarios.model.Beneficiario;
import com.socorroandino.service_beneficiarios.model.Inventario;
import com.socorroandino.service_beneficiarios.model.EntregaDonacion;
import com.socorroandino.service_beneficiarios.repository.BeneficiarioRepository;
import com.socorroandino.service_beneficiarios.repository.InventarioRepository;
import com.socorroandino.service_beneficiarios.repository.EntregaDonacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregaDonacionService {

    private final EntregaDonacionRepository entregaDonacionRepository;
    private final BeneficiarioRepository beneficiarioRepository;
    private final InventarioRepository inventarioRepository;
    private final WebClient.Builder webClientBuilder;

    // Validación inter-servicio síncrona idéntica a tu servicio de emergencias
    private void validarUsuarioResponsable(Long idUsuarioResponsable) {
        if (idUsuarioResponsable != null) {
            try {
                Object usuario = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/api/v1/usuarios/" + idUsuarioResponsable)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
                
                if (usuario == null) {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                throw new RuntimeException("Error: El usuario administrador responsable no existe en el sistema central.");
            }
        }
    }

    @Transactional
    public EntregaDonacion registrarEntrega(Long idBeneficiario, Long idInventario, Integer cantidad, Long idUsuarioResponsable) {
        // 1. Validar comunicación con microservicio externo usuarios (Puerto 8081)
        validarUsuarioResponsable(idUsuarioResponsable);

        // 2. Validar que existan los recursos en nuestra base de datos local
        Beneficiario beneficiario = beneficiarioRepository.findById(idBeneficiario)
            .orElseThrow(() -> new RuntimeException("Error: Beneficiario no encontrado."));
            
        Inventario inventario = inventarioRepository.findById(idInventario)
            .orElseThrow(() -> new RuntimeException("Error: El artículo solicitado no existe en bodega."));

        // 3. Validar regla transaccional crítica: Stock suficiente
        if (inventario.getStock() < cantidad) {
            throw new IllegalArgumentException("Error: Stock insuficiente en inventario para realizar esta entrega física.");
        }

        // 4. Modificar y actualizar el stock localmente en MySQL
        inventario.setStock(inventario.getStock() - cantidad);
        inventarioRepository.save(inventario);

        // 5. Persistir el registro histórico del evento de ayuda
        EntregaDonacion entrega = new EntregaDonacion();
        entrega.setBeneficiario(beneficiario);
        entrega.setInventario(inventario);
        entrega.setCantidadEntregada(cantidad);
        entrega.setIdUsuarioResponsable(idUsuarioResponsable);

        return entregaDonacionRepository.save(entrega);
    }

    public List<EntregaDonacion> listarTodas() {
        return entregaDonacionRepository.findAll();
    }
}