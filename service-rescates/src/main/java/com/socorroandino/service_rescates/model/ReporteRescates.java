package com.socorroandino.service_rescates.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reporte_rescates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReporteRescates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    @Column(nullable = false)
    private Long idAsignacion;

    @Column(nullable = false, length = 1000)
    private String observacionesMedicas;

    @Column(nullable = false)
    private String resultado; // EXITOSO, PARCIAL, FALLIDO

    @Column(nullable = false)
    private LocalDateTime fechaCierre;

    @PrePersist
    protected void onCreate() {
        this.fechaCierre = LocalDateTime.now();
    }
}