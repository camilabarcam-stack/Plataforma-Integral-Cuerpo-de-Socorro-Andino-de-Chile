package com.socorroandino.service_beneficiarios.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "entregas_donacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntregaDonacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEntrega;

    @ManyToOne
    @JoinColumn(name = "id_beneficiario", nullable = false)
    private Beneficiario beneficiario;

    @ManyToOne
    @JoinColumn(name = "id_inventario", nullable = false)
    private Inventario inventario;

    @Column(nullable = false)
    private Integer cantidadEntregada;

    @Column(nullable = false)
    private LocalDateTime fechaEntrega;

    @Column(nullable = false)
    private Long idUsuarioResponsable; // ID del administrador alojado en service-usuarios (8081)

    @PrePersist
    protected void onCreate() {
        this.fechaEntrega = LocalDateTime.now();
    }
}