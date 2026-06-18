package com.socorroandino.service_donaciones.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comprobantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComprobante;

    @Column(nullable = false, unique = true)
    private Long idDonacion; // Relación lógica directa con la donación aprobada

    @Column(nullable = false)
    private String codigoVerificacion; // Folio único del recibo

    private LocalDateTime fechaEmision;

    @PrePersist
    protected void onCreate() {
        this.fechaEmision = LocalDateTime.now();
    }
}