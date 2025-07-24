package edu.ifma.labd.testes;

import edu.ifma.labd.modelo.Jogo;
import edu.ifma.labd.modelo.Plataforma;
import edu.ifma.labd.modelo.PrecoPlataforma;
import edu.ifma.labd.servico.JogoService;
import java.math.BigDecimal;

public class TesteJogoService {

    public static void main(String[] args) {
        System.out.println("=== TESTES DO SERVIÇO DE JOGOS ===");

        JogoService service = new JogoService();

        // Teste 1: Cadastro de jogo
        Jogo jogo = service.cadastrarJogo("The Legend of Zelda: Breath of the Wild");
        System.out.println("Teste 1 - Cadastro de jogo: " +
                (jogo.getId() != null ? "SUCESSO" : "FALHA") +
                " | ID: " + jogo.getId());

        // Teste 2: Cadastro de plataforma
        Plataforma switchPlataforma = service.cadastrarPlataforma("Nintendo Switch");
        System.out.println("Teste 2 - Cadastro de plataforma: " +
                (switchPlataforma.getId() != null ? "SUCESSO" : "FALHA") +
                " | ID: " + switchPlataforma.getId());

        // Teste 3: Definição de preço
        PrecoPlataforma preco = service.definirPrecoJogoPlataforma(
                jogo, switchPlataforma, new BigDecimal("35.00")
        );
        System.out.println("Teste 3 - Definição de preço: " +
                (preco.getId() != null ? "SUCESSO" : "FALHA") +
                " | Preço: R$" + preco.getPrecoDiario());

        System.out.println("=== FIM DOS TESTES DE JOGOS ===");
    }
}