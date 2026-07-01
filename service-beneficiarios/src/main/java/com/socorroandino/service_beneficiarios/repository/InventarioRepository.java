package com.socorroandino.service_beneficiarios.repository;
import com.socorroandino.service_beneficiarios.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByArticulo(String articulo);
}