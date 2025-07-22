package edu.ifma.labd.modelo;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String telefone;
    private String senha;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Locacao> locacoes = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<UtilizacaoConsole> utilizacoesConsoles = new ArrayList<>();
}
