package edu.ifma.labd.testes;

import edu.ifma.labd.modelo.Cliente;
import edu.ifma.labd.servico.ClienteService;

public class TesteClienteService {

    public static void main(String[] args) {
        System.out.println("=== TESTES DO SERVIÇO DE CLIENTES ===");

        ClienteService service = new ClienteService();

        // Teste 1: Cadastro válido
        Cliente cliente1 = service.cadastrarCliente(
                "Nathalia Froz", "nathaliafroz@email.com", "(11) 1214-1151", "senha123"
        );
        System.out.println("Teste 1 - Cadastro válido: " +
                (cliente1.getId() != null ? "SUCESSO" : "FALHA") +
                " | ID: " + cliente1.getId());

        /* // Teste 2: Tentativa de email duplicado
        try {
            service.cadastrarCliente("Ana Duplicada", "ana.silva@email.com", "(22) 22222-2222", "outrasenha");
            System.out.println("Teste 2 - Email duplicado: FALHA (deveria ter bloqueado)");
        } catch (IllegalArgumentException e) {
            System.out.println("Teste 2 - Email duplicado: SUCESSO (bloqueado como esperado)");
        }

        /* // Teste 3: Busca por email
        Cliente clienteBuscado = service.buscarPorEmail("ana.silva@email.com");
        System.out.println("Teste 3 - Busca por email: " +
                (clienteBuscado != null ? "SUCESSO" : "FALHA"));

         */

        System.out.println("=== FIM DOS TESTES DE CLIENTES ===");
    }
}
