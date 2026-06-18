package com.socorroandino.service_usuarios.repository;

import com.socorroandino.service_usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}