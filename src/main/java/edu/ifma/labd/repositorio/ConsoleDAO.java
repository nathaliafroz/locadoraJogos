package edu.ifma.labd.repositorio;


import edu.ifma.labd.modelo.Console;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ConsoleDAO extends GenericDAO<Console> {

    public ConsoleDAO() {
        super(Console.class);
    }

    public List<Console> buscarPorNome(String nome) {
        return manager.createQuery(
                        "SELECT c FROM Console c WHERE c.nome LIKE :nome",
                        Console.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}