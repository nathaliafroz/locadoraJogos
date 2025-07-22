package edu.ifma.labd;

import edu.ifma.labd.modelo.Cliente;
import edu.ifma.labd.modelo.Console;
import edu.ifma.labd.modelo.Locacao;
import edu.ifma.labd.modelo.UtilizacaoConsole;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("locadora");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();

        // Criar cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Roberto Almeida");
        cliente.setEmail("roberto@email.com");
        manager.persist(cliente);

        // Criar locação
        Locacao locacao = new Locacao();
        locacao.setData(LocalDate.now());
        locacao.setCliente(cliente);
        manager.persist(locacao);

        // Adicionar locação ao cliente
        cliente.getLocacoes().add(locacao);

        // Criar console
        Console xbox = new Console();
        xbox.setNome("Xbox Series S");
        xbox.setPrecoPorHora(new BigDecimal("10.00"));
        manager.persist(xbox);

        // Criar utilização de console
        UtilizacaoConsole utilizacao = new UtilizacaoConsole();
        utilizacao.setInicio(LocalDateTime.now());
        utilizacao.setFim(LocalDateTime.now().plusHours(2));
        utilizacao.setCliente(cliente);
        utilizacao.setLocacao(locacao);
        utilizacao.setConsole(xbox);
        manager.persist(utilizacao);

        // Adicionar utilização ao cliente e à locação
        cliente.getUtilizacoesConsoles().add(utilizacao);
        locacao.getUtilizacoesConsoles().add(utilizacao);

        manager.getTransaction().commit();

        System.out.println("Cliente ID: " + cliente.getId());
        System.out.println("Locação ID: " + locacao.getId());
        System.out.println("Utilização de Console ID: " + utilizacao.getId());

        manager.close();
        factory.close();
    }
}