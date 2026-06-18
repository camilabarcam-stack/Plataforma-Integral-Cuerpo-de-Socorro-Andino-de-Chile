package com.socorroandino.service_emergencias.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "emergencias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Emergencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmergencia;

    @Column(nullable = false)
    private Long idUsuario; // ID del usuario reportante alojado en el otro microservicio

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String ubicacion;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    @Column(nullable = false)
    private String estado; // PENDIENTE, EN_PROCESO, RESCATADO

    @Transient
    private Object datosUsuario; // Atributo temporal para guardar la info que traiga WebClient

    @PrePersist
    protected void onCreate() {
        this.fechaRegistro = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = "PENDIENTE";
        }
    }
}