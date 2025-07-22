package edu.ifma.labd.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ItemLocacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Locacao locacao;

    @ManyToOne
    private PrecoPlataforma precoPlataforma;

    private Integer dias;
}
