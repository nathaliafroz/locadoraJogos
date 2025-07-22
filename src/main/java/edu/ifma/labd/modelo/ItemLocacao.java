package edu.ifma.labd.modelo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ItemLocacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer dias;

    @ManyToOne
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;

    @ManyToOne
    @JoinColumn(name = "preco_plataforma_id")
    private PrecoPlataforma precoPlataforma;
}
