package com.socorroandino.service_rescates.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "asignaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsignacion;

    @Column(nullable = false)
    private Long idEmergencia; // ID de la emergencia alojada en service-emergencias

    @Column(nullable = false)
    private Long idRescatista; // ID del rescatista asignado localmente

    @Column(nullable = false)
    private LocalDateTime fechaAsignacion;

    @Transient
    private Object datosEmergencia; // Para incrustar los datos dinámicamente

    @PrePersist
    protected void onCreate() {
        this.fechaAsignacion = LocalDateTime.now();
    }
}