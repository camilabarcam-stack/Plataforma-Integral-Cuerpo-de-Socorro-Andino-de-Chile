package com.socorroandino.service_beneficiarios.repository;
import com.socorroandino.service_beneficiarios.model.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
    Optional<Beneficiario> findByRut(String rut);
}