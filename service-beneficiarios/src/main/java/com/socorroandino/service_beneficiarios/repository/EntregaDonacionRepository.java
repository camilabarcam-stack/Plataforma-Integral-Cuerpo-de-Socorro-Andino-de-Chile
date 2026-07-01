package com.socorroandino.service_beneficiarios.repository;
import com.socorroandino.service_beneficiarios.model.EntregaDonacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaDonacionRepository extends JpaRepository<EntregaDonacion, Long> {
}