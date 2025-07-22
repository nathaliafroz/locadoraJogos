package edu.ifma.labd.servico;

import edu.ifma.labd.repositorio.ClienteDAO;
import edu.ifma.labd.modelo.Cliente;

public class ClienteService {

    private final ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    public Cliente cadastrarCliente(String nome, String email, String telefone, String senha) {
        try {
            // Verificar se email já existe
            if (clienteDAO.buscarPorEmail(email) != null) {
                throw new IllegalArgumentException("Email já cadastrado");
            }

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

    public Cliente buscarClientePorEmail(String email) {
        try {
            return clienteDAO.buscarPorEmail(email);
        } finally {
            clienteDAO.fechar();
        }
    }
}