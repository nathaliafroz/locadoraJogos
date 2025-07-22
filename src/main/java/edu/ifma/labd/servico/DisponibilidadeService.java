package edu.ifma.labd.servico;

import edu.ifma.labd.repositorio.ItemLocacaoDAO;
import edu.ifma.labd.repositorio.UtilizacaoConsoleDAO;
import edu.ifma.labd.modelo.PrecoPlataforma;
import edu.ifma.labd.modelo.Console;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DisponibilidadeService {

    private final ItemLocacaoDAO itemLocacaoDAO;
    private final UtilizacaoConsoleDAO utilizacaoConsoleDAO;

    public DisponibilidadeService() {
        this.itemLocacaoDAO = new ItemLocacaoDAO();
        this.utilizacaoConsoleDAO = new UtilizacaoConsoleDAO();
    }

    public boolean isJogoDisponivel(PrecoPlataforma precoPlataforma, LocalDate data) {
        try {
            return itemLocacaoDAO.buscarPorPrecoEData(precoPlataforma, data).isEmpty();
        } finally {
            itemLocacaoDAO.fechar();
        }
    }

    public boolean isConsoleDisponivel(Console console, LocalDateTime inicio, LocalDateTime fim) {
        try {
            return utilizacaoConsoleDAO.buscarPorConsoleEPeriodo(console, inicio, fim).isEmpty();
        } finally {
            utilizacaoConsoleDAO.fechar();
        }
    }
}