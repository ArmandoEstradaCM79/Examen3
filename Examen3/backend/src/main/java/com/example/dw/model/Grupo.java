
package com.example.dw.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grupo;
    private String semestre;
}
