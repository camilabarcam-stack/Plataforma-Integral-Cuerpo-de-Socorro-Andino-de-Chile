package com.socorroandino.service_beneficiarios.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    @Column(nullable = false, unique = true)
    private String articulo; // Ej: "Mantas", "Agua Mineral", "Kits de Primeros Auxilios"

    @Column(nullable = false)
    private Integer stock;
}