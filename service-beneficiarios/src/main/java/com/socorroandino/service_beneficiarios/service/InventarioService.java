package com.socorroandino.service_beneficiarios.service;

import com.socorroandino.service_beneficiarios.model.Inventario;
import com.socorroandino.service_beneficiarios.repository.InventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public Inventario cargarOActualizar(Inventario item) {
        // Si el artículo ya existe (ej: "Mantas"), suma el nuevo stock al existente
        return inventarioRepository.findByArticulo(item.getArticulo())
            .map(existente -> {
                existente.setStock(existente.getStock() + item.getStock());
                return inventarioRepository.save(existente);
            })
            .orElseGet(() -> inventarioRepository.save(item));
    }

    public List<Inventario> listarTodo() {
        return inventarioRepository.findAll();
    }
}