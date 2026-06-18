package com.socorroandino.service_rescates.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rescatistas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rescatista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRescatista;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String especialidad; // Alta Montaña, Primeros Auxilios, Rastreo

    @Column(nullable = false)
    private String estadoAvailability; // DISPONIBLE, EN_MISION, INACTIVO
}