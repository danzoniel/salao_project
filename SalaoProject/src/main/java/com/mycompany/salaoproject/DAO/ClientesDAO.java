package com.mycompany.salaoproject.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.salaoproject.models.Clientes;

public class ClientesDAO {
    private HelperDAO helperDAO;

    public ClientesDAO(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
    }

    // método para obter um usuário específico do banco de dados
    // public Clientes getEmail(String clienteEmail) throws SQLException {
    //     Usuario usuario = null;
    //     String query = "SELECT * FROM cliente WHERE email = ?";
    //     try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
    //         statement.setString(1, usuarioEmail);
    //         ResultSet resultSet = statement.executeQuery();
    //         if (resultSet.next()) {
    //             usuario = new Usuario(
    //                 resultSet.getString("email"),
    //                 resultSet.getString("senha")
    //             );
    //         }
    //     }
    //     return usuario;
    // }

    public List<String> getEmailString() throws SQLException {
        List<String> emails = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String email = resultSet.getString("email");
                emails.add(email);
            }
        }
        return emails;
    }

    public List<Clientes> getClientes() throws SQLException {
        List<Clientes> clients = new ArrayList<>();
        String query = "SELECT * FROM cliente";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Clientes client = new Clientes(
                    resultSet.getString("email"),
                    resultSet.getString("nome")
                );
                clients.add(client);
            }
        }
        return clients;
    }
}
