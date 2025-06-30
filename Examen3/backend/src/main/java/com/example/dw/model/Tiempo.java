
package com.example.dw.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Tiempo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int anio;
    private String semestre;
}
