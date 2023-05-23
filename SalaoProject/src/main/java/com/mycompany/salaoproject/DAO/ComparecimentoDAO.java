package com.mycompany.salaoproject.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.salaoproject.models.Agendamento;
import com.mycompany.salaoproject.models.Comparecimento;


public class ComparecimentoDAO {

    private HelperDAO helperDAO;

    public ComparecimentoDAO(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
    }

    public Comparecimento getComparecimento(int id_comparecimento) throws SQLException {
        Comparecimento comparecimento = null;
        String query = "SELECT * FROM comparecimento WHERE id_comparecimento = ?";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setInt(1, id_comparecimento);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                comparecimento = new Comparecimento(
                    resultSet.getInt("id_comparecimento"),
                    resultSet.getString("email_cliente"),
                    resultSet.getTimestamp("data_agendamento")
                );
            }
        }
        return comparecimento;
    }

    public List<Comparecimento> getComparecimentos() throws SQLException {
        List<Comparecimento> comparecimentos = new ArrayList<>();
        String query = "SELECT * FROM comparecimento";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Comparecimento comparecimento = new Comparecimento(
                    resultSet.getInt("id_comparecimento"),
                    resultSet.getString("email_cliente"),
                    resultSet.getTimestamp("data_agendamento")
                );
                comparecimentos.add(comparecimento);
            }
        }
        return comparecimentos;
    }

    public boolean existeComparecimento(Agendamento agendamento) throws SQLException {
        String query = "SELECT COUNT(*) FROM comparecimento WHERE email_cliente = ? AND data_agendamento = ?";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setString(1, agendamento.getEmailCliente());
            statement.setDate(2, agendamento.getDataAgendamento());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    public void criarComparecimento(Comparecimento comparecimento) throws SQLException {
        String query = "INSERT INTO comparecimento (email_cliente, data_agendamento) VALUES (?, ?)";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setString(1, comparecimento.getEmail_cliente());
            statement.setTimestamp(2, comparecimento.getData_agendamento());
            statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    

    public int contarComparecimentosPorEmail(String email) throws SQLException {
        int count = 0;
        String query = "SELECT COUNT(*) FROM comparecimento WHERE email_cliente = ?";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
            }
        }
        return count;
    }

    public int obterIdGerado() throws SQLException {
        String query = "SELECT LAST_INSERT_ID()";
        try (Statement statement = helperDAO.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        throw new SQLException("Não foi possível obter o ID gerado.");
    }

}
