package edu.ifma.labd.repositorio;

import edu.ifma.labd.modelo.Cliente;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class ClienteDAO extends GenericDAO<Cliente> {

    public ClienteDAO() {
        super(Cliente.class);
    }

    public Cliente buscarPorEmail(String email) {
        try {
            return manager.createQuery(
                            "SELECT c FROM Cliente c WHERE c.email = :email",
                            Cliente.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}