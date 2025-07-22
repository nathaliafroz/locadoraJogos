package edu.ifma.labd.modelo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Locacao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL)
    private List<ItemLocacao> itens = new ArrayList<>();

    @OneToMany(mappedBy = "locacao", cascade = CascadeType.ALL)
    private List<UtilizacaoConsole> utilizacoesConsoles = new ArrayList<>();
}

