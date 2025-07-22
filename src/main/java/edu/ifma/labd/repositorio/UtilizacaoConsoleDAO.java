package edu.ifma.labd.repositorio;


import edu.ifma.labd.modelo.Console;
import edu.ifma.labd.modelo.UtilizacaoConsole;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

public class UtilizacaoConsoleDAO extends GenericDAO<UtilizacaoConsole> {

    public UtilizacaoConsoleDAO() {
        super(UtilizacaoConsole.class);
    }

    public List<UtilizacaoConsole> buscarPorConsoleEPeriodo(Console console, LocalDateTime inicio, LocalDateTime fim) {
        return manager.createQuery(
                        "SELECT uc FROM UtilizacaoConsole uc " +
                                "WHERE uc.console = :console " +
                                "AND (uc.inicio BETWEEN :inicio AND :fim " +
                                "OR uc.fim BETWEEN :inicio AND :fim " +
                                "OR :inicio BETWEEN uc.inicio AND uc.fim)",
                        UtilizacaoConsole.class)
                .setParameter("console", console)
                .setParameter("inicio", inicio)
                .setParameter("fim", fim)
                .getResultList();
    }
}