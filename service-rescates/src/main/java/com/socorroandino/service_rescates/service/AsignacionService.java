package com.socorroandino.service_rescates.service;

import com.socorroandino.service_rescates.model.Asignacion;
import com.socorroandino.service_rescates.model.Rescatista;
import com.socorroandino.service_rescates.repository.AsignacionRepository;
import com.socorroandino.service_rescates.repository.RescatistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AsignacionService {

    private final AsignacionRepository asignacionRepository;
    private final RescatistaRepository rescatistaRepository;
    private final WebClient.Builder webClientBuilder;

    private void enriquecerConEmergencia(Asignacion asignacion) {
        if (asignacion.getIdEmergencia() != null) {
            try {
                // Se conecta al puerto 8084 de service-emergencias
                Object emergencia = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8084/api/v1/emergencias/" + asignacion.getIdEmergencia())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
                asignacion.setDatosEmergencia(emergencia);
            } catch (Exception e) {
                asignacion.setDatosEmergencia(null);
            }
        }
    }

    public List<Asignacion> listarTodas() {
        List<Asignacion> asignaciones = asignacionRepository.findAll();
        asignaciones.forEach(this::enriquecerConEmergencia);
        return asignaciones;
    }

    public Asignacion crearAsignacion(Asignacion asignacion) {
        // 1. Validar localmente que el rescatista exista y esté disponible
        Rescatista r = rescatistaRepository.findById(asignacion.getIdRescatista()).orElse(null);
        if (r == null) {
            throw new RuntimeException("Error: El rescatista seleccionado no existe.");
        }
        
        // 2. Validar mediante WebClient que la emergencia exista en el sistema externo
        enriquecerConEmergencia(asignacion);
        if (asignacion.getDatosEmergencia() == null) {
            throw new RuntimeException("Error: La emergencia no existe en el sistema.");
        }

        // 3. Cambiar estado del rescatista en terreno
        r.setEstadoAvailability("EN_MISION");
        rescatistaRepository.save(r);

        return asignacionRepository.save(asignacion);
    }
}