package edu.ifma.labd.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory factory;

    static {
        try {
            factory = Persistence.createEntityManagerFactory("locadora");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar EntityManagerFactory", e);
        }
    }

    public static EntityManager getEntityManager() {
        if (factory == null || !factory.isOpen()) {
            throw new IllegalStateException("EntityManagerFactory está fechado");
        }
        return factory.createEntityManager();
    }

    public static void close() {
        if (factory != null && factory.isOpen()) {
            factory.close();
        }
    }

    public static void rollback(EntityManager manager) {
        if (manager != null && manager.getTransaction().isActive()) {
            manager.getTransaction().rollback();
        }
    }
}