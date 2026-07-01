package com.socorroandino.service_beneficiarios.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "beneficiarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBeneficiario;

    @Column(nullable = false, unique = true)
    private String rut;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private String ubicacion; // Dirección escrita en texto plano, sin APIs externas
}