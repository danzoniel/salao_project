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

    public void addCliente(Clientes cliente) throws SQLException {
        String query = "INSERT INTO cliente (email, nome) VALUES (?, ?)";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getNome());
            statement.executeUpdate();
        }
    }

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

    public List<Clientes> getNomes(String filtro) throws SQLException {
        List<Clientes> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente";

        if (filtro != null && !filtro.isEmpty()) {
            query += " WHERE nome LIKE ?";
        }

        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            if (filtro != null && !filtro.isEmpty()) {
                statement.setString(1, "%" + filtro + "%");
            }   

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Clientes cliente = new Clientes(
                        resultSet.getString("email"),
                        resultSet.getString("nome")
                    );
                    clientes.add(cliente);
            }   }
        }
        return clientes;
    }

}
