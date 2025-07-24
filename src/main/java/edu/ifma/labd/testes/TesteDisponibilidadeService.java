package edu.ifma.labd.testes;

import edu.ifma.labd.modelo.*;
import edu.ifma.labd.servico.ClienteService;
import edu.ifma.labd.servico.DisponibilidadeService;
import edu.ifma.labd.servico.JogoService;
import edu.ifma.labd.servico.LocacaoService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TesteDisponibilidadeService {

    public static void main(String[] args) {
        System.out.println("=== TESTES DO SERVIÇO DE DISPONIBILIDADE ===");

        // Configurar dados de teste
        ClienteService clienteService = new ClienteService();
        JogoService jogoService = new JogoService();
        LocacaoService locacaoService = new LocacaoService();
        DisponibilidadeService disponibilidadeService = new DisponibilidadeService();

        Cliente cliente = clienteService.cadastrarCliente(
                "Teste Disponibilidade", "disp2@teste2.com", "(55) 55555-5555", "senha000"
        );

        Jogo jogo = jogoService.cadastrarJogo("Elden Ring");
        Plataforma pc = jogoService.cadastrarPlataforma("PC");
        PrecoPlataforma preco = jogoService.definirPrecoJogoPlataforma(jogo, pc, new BigDecimal("40.00"));

        Console console = new Console();
        console.setNome("PC Gamer");
        console.setPrecoPorHora(new BigDecimal("25.00"));

        LocalDate hoje = LocalDate.now();
        LocalDateTime agora = LocalDateTime.now();

        // Teste 1: Disponibilidade inicial
        boolean disponivelInicio = disponibilidadeService.isJogoDisponivel(preco, hoje);
        System.out.println("Teste 1 - Jogo disponível inicialmente: " +
                (disponivelInicio ? "SUCESSO" : "FALHA"));

        // Teste 2: Disponibilidade de console inicial
        boolean consoleDisponivel = disponibilidadeService.isConsoleDisponivel(
                console, agora, agora.plusHours(2)
        );
        System.out.println("Teste 2 - Console disponível inicialmente: " +
                (consoleDisponivel ? "SUCESSO" : "FALHA"));

        // Realizar locações
        LocacaoService.ItemLocacaoRequest item = new LocacaoService.ItemLocacaoRequest();
        item.setPrecoPlataformaId(preco.getId());
        item.setDias(3);
        locacaoService.realizarLocacao(cliente, List.of(item));

        // Teste 3: Jogo indisponível após locação
        boolean disponivelDepois = disponibilidadeService.isJogoDisponivel(preco, hoje);
        System.out.println("Teste 3 - Jogo indisponível após locação: " +
                (!disponivelDepois ? "SUCESSO" : "FALHA"));

        System.out.println("=== FIM DOS TESTES DE DISPONIBILIDADE ===");
    }
}