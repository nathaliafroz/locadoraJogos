package edu.ifma.labd.servico;

import edu.ifma.labd.repositorio.*;
import edu.ifma.labd.modelo.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LocacaoService {

    private final LocacaoDAO locacaoDAO;
    private final ItemLocacaoDAO itemLocacaoDAO;
    private final PrecoPlataformaDAO precoPlataformaDAO;
    private final ClienteDAO clienteDAO;

    public LocacaoService() {
        this.locacaoDAO = new LocacaoDAO();
        this.itemLocacaoDAO = new ItemLocacaoDAO();
        this.precoPlataformaDAO = new PrecoPlataformaDAO();
        this.clienteDAO = new ClienteDAO();
    }

    public Locacao realizarLocacao(Cliente cliente, List<ItemLocacaoRequest> itens) {
        try {
            // Cria a locação
            Locacao locacao = new Locacao();
            locacao.setData(LocalDate.now());
            locacao.setCliente(cliente);
            locacaoDAO.salvar(locacao);

            // Processa os itens da locação
            List<ItemLocacao> itensLocacao = new ArrayList<>();
            BigDecimal valorTotal = BigDecimal.ZERO;

            for (ItemLocacaoRequest item : itens) {
                PrecoPlataforma precoPlataforma = precoPlataformaDAO.buscarPorId(item.getPrecoPlataformaId());

                if (precoPlataforma == null) {
                    throw new IllegalArgumentException("Preço/Plataforma não encontrado");
                }

                ItemLocacao itemLoc = new ItemLocacao();
                itemLoc.setLocacao(locacao);
                itemLoc.setPrecoPlataforma(precoPlataforma);
                itemLoc.setDias(item.getDias());
                itemLocacaoDAO.salvar(itemLoc);
                itensLocacao.add(itemLoc);

                // Calcula valor do item
                BigDecimal valorItem = precoPlataforma.getPrecoDiario()
                        .multiply(BigDecimal.valueOf(item.getDias()));
                valorTotal = valorTotal.add(valorItem);
            }

            // Atualiza locação com itens e valor total
            locacao.setItens(itensLocacao);
            locacao.setValorTotal(valorTotal);
            locacaoDAO.atualizar(locacao);

            return locacao;
        } finally {
            locacaoDAO.fechar();
            itemLocacaoDAO.fechar();
            precoPlataformaDAO.fechar();
            clienteDAO.fechar();
        }
    }

    public static class ItemLocacaoRequest {
        private Integer precoPlataformaId;
        private Integer dias;

        // Getters e Setters
        public Integer getPrecoPlataformaId() {
            return precoPlataformaId;
        }

        public void setPrecoPlataformaId(Integer precoPlataformaId) {
            this.precoPlataformaId = precoPlataformaId;
        }

        public Integer getDias() {
            return dias;
        }

        public void setDias(Integer dias) {
            this.dias = dias;
        }
    }
}