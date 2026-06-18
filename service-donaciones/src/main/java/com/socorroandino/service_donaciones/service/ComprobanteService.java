package com.socorroandino.service_donaciones.service;

import com.socorroandino.service_donaciones.model.Comprobante;
import com.socorroandino.service_donaciones.repository.ComprobanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComprobanteService {

    private final ComprobanteRepository repository;

    public List<Comprobante> listarTodos() {
        return repository.findAll();
    }

    public Comprobante buscarPorDonacion(Long idDonacion) {
        return repository.findByIdDonacion(idDonacion);
    }
}