package edu.ifma.labd.testes;

import edu.ifma.labd.modelo.Cliente;
import edu.ifma.labd.modelo.Console;
import edu.ifma.labd.modelo.UtilizacaoConsole;
import edu.ifma.labd.servico.ClienteService;
import edu.ifma.labd.servico.ConsoleService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class TesteConsoleService {

    public static void main(String[] args) {
        System.out.println("=== TESTES DO SERVIÇO DE LOCAÇÃO DE CONSOLES ===");

        ClienteService clienteService = new ClienteService();
        ConsoleService consoleService = new ConsoleService();

        Cliente cliente = clienteService.cadastrarCliente(
                "Fernanda Console", "fernanda@console.com", "(44) 44444-4444", "senha789"
        );

        Console xbox = new Console();
        xbox.setNome("Xbox Series X");
        xbox.setPrecoPorHora(new BigDecimal("18.00"));

        // Teste 1: Locação válida
        UtilizacaoConsole utilizacao = consoleService.locarConsole(
                cliente,
                xbox,
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(4),
                List.of() // Sem acessórios
        );

        System.out.println("Teste 1 - Locação de console: " +
                (utilizacao.getId() != null ? "SUCESSO" : "FALHA") +
                " | Valor: R$" + utilizacao.getValor());

        System.out.println("=== FIM DOS TESTES DE LOCAÇÃO DE CONSOLES ===");
    }
}