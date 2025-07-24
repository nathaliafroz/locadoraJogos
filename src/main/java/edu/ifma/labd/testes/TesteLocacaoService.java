package edu.ifma.labd.testes;

import edu.ifma.labd.modelo.*;
import edu.ifma.labd.servico.ClienteService;
import edu.ifma.labd.servico.JogoService;
import edu.ifma.labd.servico.LocacaoService;
import java.math.BigDecimal;
import java.util.List;

public class TesteLocacaoService {

    public static void main(String[] args) {
        System.out.println("=== TESTES DO SERVIÇO DE LOCAÇÃO DE JOGOS ===");

        // Configurar dados de teste
        ClienteService clienteService = new ClienteService();
        JogoService jogoService = new JogoService();
        LocacaoService locacaoService = new LocacaoService();

        Cliente cliente = clienteService.cadastrarCliente(
                "Carlos Locatário", "carlos@locacao.com", "(33) 33333-3333", "senha456"
        );

        Jogo jogo = jogoService.cadastrarJogo("Red Dead Redemption 2");
        Plataforma ps4 = jogoService.cadastrarPlataforma("PS4");
        PrecoPlataforma preco = jogoService.definirPrecoJogoPlataforma(jogo, ps4, new BigDecimal("25.00"));

        // Teste 1: Locação válida
        LocacaoService.ItemLocacaoRequest item = new LocacaoService.ItemLocacaoRequest();
        item.setPrecoPlataformaId(preco.getId());
        item.setDias(5);

        Locacao locacao = locacaoService.realizarLocacao(cliente, List.of(item));
        System.out.println("Teste 1 - Locação válida: " +
                (locacao.getId() != null ? "SUCESSO" : "FALHA") +
                " | Valor Total: R$" + locacao.getValorTotal());

        System.out.println("=== FIM DOS TESTES DE LOCAÇÃO DE JOGOS ===");
    }
}