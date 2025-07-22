package edu.ifma.labd.repositorio;

import edu.ifma.labd.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public abstract class GenericDAO<T> {

    protected final EntityManager manager;
    private final Class<T> clazz;

    public GenericDAO(Class<T> clazz) {
        this.manager = JPAUtil.getEntityManager(); // Obtém do JPAUtil
        this.clazz = clazz;
    }

    public void salvar(T entity) {
        try {
            manager.getTransaction().begin();
            manager.persist(entity);
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public T atualizar(T entity) {
        try {
            manager.getTransaction().begin();
            T updated = manager.merge(entity);
            manager.getTransaction().commit();
            return updated;
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public void remover(T entity) {
        try {
            manager.getTransaction().begin();
            manager.remove(manager.contains(entity) ? entity : manager.merge(entity));
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            throw e;
        }
    }

    public T buscarPorId(Integer id) {
        return manager.find(clazz, id);
    }

    public List<T> todos() {
        return manager.createQuery("FROM " + clazz.getSimpleName(), clazz).getResultList();
    }

    public void fechar() {
        if (manager != null && manager.isOpen()) {
            manager.close();
        }
    }
}