package edu.ifma.labd.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Console {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private BigDecimal precoPorHora;

    @OneToMany(mappedBy = "console", cascade = CascadeType.ALL)
    private List<Acessorio> acessorios = new ArrayList<>();
}