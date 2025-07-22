package edu.ifma.labd.servico;

import edu.ifma.labd.repositorio.*;
import edu.ifma.labd.modelo.*;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class ConsoleService {

    private final ConsoleDAO consoleDAO;
    private final UtilizacaoConsoleDAO utilizacaoConsoleDAO;
    private final ClienteDAO clienteDAO;
    private final AcessorioDAO acessorioDAO;

    public ConsoleService() {
        this.consoleDAO = new ConsoleDAO();
        this.utilizacaoConsoleDAO = new UtilizacaoConsoleDAO();
        this.clienteDAO = new ClienteDAO();
        this.acessorioDAO = new AcessorioDAO();
    }

    public UtilizacaoConsole locarConsole(Cliente cliente, Console console,
                                          LocalDateTime inicio, LocalDateTime fim,
                                          List<Acessorio> acessorios) {
        try {
            // Calcula horas de uso (arredondando para cima)
            long horas = Duration.between(inicio, fim).toHours();
            if (Duration.between(inicio, fim).toMinutesPart() > 0) {
                horas++; // Fração de hora conta como hora completa
            }

            BigDecimal valorConsole = console.getPrecoPorHora().multiply(BigDecimal.valueOf(horas));

            // Cria a utilização
            UtilizacaoConsole utilizacao = new UtilizacaoConsole();
            utilizacao.setCliente(cliente);
            utilizacao.setConsole(console);
            utilizacao.setInicio(inicio);
            utilizacao.setFim(fim);
            utilizacao.setValor(valorConsole);
            utilizacao.setAcessorios(acessorios);

            utilizacaoConsoleDAO.salvar(utilizacao);
            return utilizacao;
        } finally {
            consoleDAO.fechar();
            utilizacaoConsoleDAO.fechar();
            clienteDAO.fechar();
            acessorioDAO.fechar();
        }
    }
}