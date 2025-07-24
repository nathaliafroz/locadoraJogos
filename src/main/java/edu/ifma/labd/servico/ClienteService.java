package edu.ifma.labd.servico;

import edu.ifma.labd.repositorio.ClienteDAO;
import edu.ifma.labd.modelo.Cliente;

public class ClienteService {
    private final ClienteDAO clienteDAO = new ClienteDAO();

    public Cliente cadastrarCliente(String nome, String email, String telefone, String senha) {
        try {
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setTelefone(telefone);
            cliente.setSenha(senha);

            clienteDAO.salvar(cliente);
            return cliente;
        } finally {
            clienteDAO.fechar();
        }
    }
}