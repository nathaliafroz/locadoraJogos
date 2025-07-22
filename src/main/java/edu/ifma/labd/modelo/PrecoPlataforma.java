package edu.ifma.labd.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
public class PrecoPlataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Jogo jogo;

    @ManyToOne
    private Plataforma plataforma;

    private BigDecimal precoDiario;
}