package edu.ifma.labd.repositorio;


import edu.ifma.labd.modelo.ItemLocacao;
import edu.ifma.labd.modelo.PrecoPlataforma;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class ItemLocacaoDAO extends GenericDAO<ItemLocacao> {

    public ItemLocacaoDAO() {
        super(ItemLocacao.class);
    }

    public List<ItemLocacao> buscarPorPrecoEData(PrecoPlataforma precoPlataforma, LocalDate data) {
            return manager.createQuery(
                            "SELECT il FROM ItemLocacao il " +
                                    "WHERE il.precoPlataforma = :preco " +
                                    "AND :data BETWEEN il.locacao.data " +
                                    "AND FUNCTION('ADDDATE', il.locacao.data, il.dias)",
                            ItemLocacao.class)
                    .setParameter("preco", precoPlataforma)
                    .setParameter("data", data)
                    .getResultList();

    }
}