package com.rancho.estoque_api.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;



@Data
@Entity
@Table(name = "alimentos")
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer quantidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDeposito tipoDeposito;

    private LocalDate dataValidade;
}
