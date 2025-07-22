package edu.ifma.labd.repositorio;

import edu.ifma.labd.modelo.Jogo;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class JogoDAO extends GenericDAO<Jogo> {

    public JogoDAO() {
        super(Jogo.class);
    }

    public List<Jogo> buscarPorTitulo(String titulo) {
        return manager.createQuery(
                        "SELECT j FROM Jogo j WHERE j.titulo LIKE :titulo",
                        Jogo.class)
                .setParameter("titulo", "%" + titulo + "%")
                .getResultList();
    }
}