package edu.ifma.labd.repositorio;


import edu.ifma.labd.modelo.Cliente;
import edu.ifma.labd.modelo.Locacao;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class LocacaoDAO extends GenericDAO<Locacao> {

    public LocacaoDAO() {
        super(Locacao.class);
    }

    public List<Locacao> buscarPorCliente(Cliente cliente) {
        return manager.createQuery(
                        "SELECT l FROM Locacao l WHERE l.cliente = :cliente",
                        Locacao.class)
                .setParameter("cliente", cliente)
                .getResultList();
    }

    public List<Locacao> buscarPorData(LocalDate data) {
        return manager.createQuery(
                        "SELECT l FROM Locacao l WHERE l.data = :data",
                        Locacao.class)
                .setParameter("data", data)
                .getResultList();
    }
}