package edu.ifma.labd.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Acessorio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @ManyToOne
    @JoinColumn(name = "console_id")
    private Console console;
}