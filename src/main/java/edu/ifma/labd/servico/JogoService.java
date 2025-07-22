package edu.ifma.labd.servico;

import edu.ifma.labd.repositorio.JogoDAO;
import edu.ifma.labd.repositorio.PlataformaDAO;
import edu.ifma.labd.repositorio.PrecoPlataformaDAO;
import edu.ifma.labd.modelo.Jogo;
import edu.ifma.labd.modelo.Plataforma;
import edu.ifma.labd.modelo.PrecoPlataforma;
import java.math.BigDecimal;

public class JogoService {

    private final JogoDAO jogoDAO;
    private final PlataformaDAO plataformaDAO;
    private final PrecoPlataformaDAO precoPlataformaDAO;

    public JogoService() {
        this.jogoDAO = new JogoDAO();
        this.plataformaDAO = new PlataformaDAO();
        this.precoPlataformaDAO = new PrecoPlataformaDAO();
    }

    public Jogo cadastrarJogo(String titulo) {
        try {
            Jogo jogo = new Jogo();
            jogo.setTitulo(titulo);
            jogoDAO.salvar(jogo);
            return jogo;
        } finally {
            jogoDAO.fechar();
        }
    }

    public Plataforma cadastrarPlataforma(String nome) {
        try {
            Plataforma plataforma = new Plataforma();
            plataforma.setNome(nome);
            plataformaDAO.salvar(plataforma);
            return plataforma;
        } finally {
            plataformaDAO.fechar();
        }
    }

    public PrecoPlataforma definirPrecoJogoPlataforma(Jogo jogo, Plataforma plataforma, BigDecimal precoDiario) {
        try {
            // Verifica se o preço já existe
            PrecoPlataforma precoExistente = precoPlataformaDAO.buscarPorJogoEPLataforma(jogo, plataforma);
            if (precoExistente != null) {
                throw new IllegalArgumentException("Preço já definido para esta combinação");
            }

            PrecoPlataforma preco = new PrecoPlataforma();
            preco.setJogo(jogo);
            preco.setPlataforma(plataforma);
            preco.setPrecoDiario(precoDiario);

            precoPlataformaDAO.salvar(preco);
            return preco;
        } finally {
            precoPlataformaDAO.fechar();
        }
    }
}