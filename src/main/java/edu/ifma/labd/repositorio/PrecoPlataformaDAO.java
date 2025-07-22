package edu.ifma.labd.repositorio;



import edu.ifma.labd.modelo.Jogo;
import edu.ifma.labd.modelo.Plataforma;
import edu.ifma.labd.modelo.PrecoPlataforma;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class PrecoPlataformaDAO extends GenericDAO<PrecoPlataforma> {

    public PrecoPlataformaDAO() {
        super(PrecoPlataforma.class);
    }

    public PrecoPlataforma buscarPorJogoEPLataforma(Jogo jogo, Plataforma plataforma) {
        try {
            return manager.createQuery(
                            "SELECT pp FROM PrecoPlataforma pp " +
                                    "WHERE pp.jogo = :jogo AND pp.plataforma = :plataforma",
                            PrecoPlataforma.class)
                    .setParameter("jogo", jogo)
                    .setParameter("plataforma", plataforma)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<PrecoPlataforma> buscarPorJogo(Jogo jogo) {
        return manager.createQuery(
                        "SELECT pp FROM PrecoPlataforma pp WHERE pp.jogo = :jogo",
                        PrecoPlataforma.class)
                .setParameter("jogo", jogo)
                .getResultList();
    }
}