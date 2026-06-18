package com.socorroandino.service_rescates.service;

import com.socorroandino.service_rescates.model.Rescatista;
import com.socorroandino.service_rescates.repository.RescatistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RescatistaService {

    private final RescatistaRepository rescatistaRepository;

    public List<Rescatista> listarTodos() {
        return rescatistaRepository.findAll();
    }

    public Rescatista buscarPorId(Long id) {
        return rescatistaRepository.findById(id).orElse(null);
    }

    public Rescatista guardarRescatista(Rescatista rescatista) {
        return rescatistaRepository.save(rescatista);
    }
}