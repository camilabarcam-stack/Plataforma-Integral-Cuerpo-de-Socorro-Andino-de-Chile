package com.socorroandino.service_donaciones.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "donaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDonacion;

    @Column(nullable = false)
    private Long idUsuario; // ID del donante alojado en service-usuarios

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private String metodoPago; // CREDITO, DEBITO, TRANSFERENCIA

    @Column(nullable = false)
    private String estado; // PENDIENTE, COMPLETADA, RECHAZADA

    private LocalDateTime fechaDonacion;

    @Transient
    private Object datosUsuario; // Guardará la info que traiga WebClient del usuario

    @PrePersist
    protected void onCreate() {
        this.fechaDonacion = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = "PENDIENTE";
        }
    }
}