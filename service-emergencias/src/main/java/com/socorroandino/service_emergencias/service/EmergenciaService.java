package com.socorroandino.service_emergencias.service;

import com.socorroandino.service_emergencias.model.Emergencia;
import com.socorroandino.service_emergencias.repository.EmergenciaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmergenciaService {

    private final EmergenciaRepository emergenciaRepository;
    private final WebClient.Builder webClientBuilder;

    private void enriquecerConUsuario(Emergencia emergencia) {
        if (emergencia.getIdUsuario() != null) {
            try {
                Object usuario = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8081/api/v1/usuarios/" + emergencia.getIdUsuario())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
                emergencia.setDatosUsuario(usuario);
            } catch (Exception e) {
                emergencia.setDatosUsuario(null);
            }
        }
    }

    public List<Emergencia> listarTodas() {
        List<Emergencia> emergencias = emergenciaRepository.findAll();
        emergencias.forEach(this::enriquecerConUsuario);
        return emergencias;
    }

    public Emergencia buscarPorId(Long id) {
        Emergencia emergencia = emergenciaRepository.findById(id).orElse(null);
        if (emergencia != null) {
            enriquecerConUsuario(emergencia);
        }
        return emergencia;
    }

    public Emergencia registrarEmergencia(Emergencia emergencia) {
        enriquecerConUsuario(emergencia);
        if (emergencia.getDatosUsuario() == null) {
            throw new RuntimeException("Error: El usuario reportante no existe en el sistema.");
        }
        return emergenciaRepository.save(emergencia);
    }

    public Emergencia actualizarEstado(Long id, String nuevoEstado) {
        Emergencia emergencia = emergenciaRepository.findById(id).orElse(null);
        if (emergencia != null) {
            emergencia.setEstado(nuevoEstado);
            Emergencia guardada = emergenciaRepository.save(emergencia);
            enriquecerConUsuario(guardada);
            return guardada;
        }
        return null;
    }
}