package com.socorroandino.service_donaciones.service;

import com.socorroandino.service_donaciones.model.Donacion;
import com.socorroandino.service_donaciones.model.Comprobante;
import com.socorroandino.service_donaciones.repository.DonacionRepository;
import com.socorroandino.service_donaciones.repository.ComprobanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DonacionService {

    private final DonacionRepository donacionRepository;
    private final ComprobanteRepository comprobanteRepository;
    private final WebClient.Builder webClientBuilder;

    private void enriquecerConUsuario(Donacion donacion) {
        if (donacion.getIdUsuario() != null) {
            try {
                // Se conecta al puerto 8081 de service-usuarios
                Object usuario = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/api/v1/usuarios/" + donacion.getIdUsuario())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
                donacion.setDatosUsuario(usuario);
            } catch (Exception e) {
                donacion.setDatosUsuario(null);
            }
        }
    }

    public List<Donacion> listarTodas() {
        List<Donacion> donaciones = donacionRepository.findAll();
        donaciones.forEach(this::enriquecerConUsuario);
        return donaciones;
    }

    public Donacion procesarDonacion(Donacion donacion) {
        // 1. Validar mediante WebClient que el donante exista en el servicio de usuarios
        enriquecerConUsuario(donacion);
        if (donacion.getDatosUsuario() == null) {
            throw new RuntimeException("Error: El usuario donante no existe en el sistema.");
        }

        // 2. Simular aprobación exitosa de la pasarela de pagos
        donacion.setEstado("COMPLETADA");
        Donacion donacionGuardada = donacionRepository.save(donacion);

        // 3. Generar automáticamente el Comprobante/Certificado de Donación
        Comprobante comprobante = new Comprobante();
        comprobante.setIdDonacion(donacionGuardada.getIdDonacion());
        // Generamos un código de folio único alfanumérico
        comprobante.setCodigoVerificacion("CSA-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        comprobanteRepository.save(comprobante);

        return donacionGuardada;
    }

    public List<Donacion> listarPorUsuario(Long idUsuario) {
        return donacionRepository.findByIdUsuario(idUsuario);
    }
}