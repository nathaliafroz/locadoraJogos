package edu.ifma.labd.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UtilizacaoConsole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "locacao_id")
    private Locacao locacao;

    @ManyToOne
    @JoinColumn(name = "console_id")
    private Console console;

    // Novo campo: valor da utilização
    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    // Novo campo: acessórios utilizados
    @ManyToMany
    @JoinTable(
            name = "utilizacao_acessorio",
            joinColumns = @JoinColumn(name = "utilizacao_id"),
            inverseJoinColumns = @JoinColumn(name = "acessorio_id")
    )
    private List<Acessorio> acessorios = new ArrayList<>();
}