package com.mycompany.salaoproject.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.salaoproject.models.Agendamento;

public class AgendamentoDAO {
    private HelperDAO helperDAO;

    public AgendamentoDAO(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
    }

    public void criarAgendamento(Agendamento agendamento) throws SQLException {
        String sql = "INSERT INTO agendamento (email_cliente, data_agendamento, hora_agendamento) VALUES (?, ?, ?)";
        try (Connection connection = helperDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, agendamento.getEmailCliente());
            statement.setDate(2, agendamento.getDataAgendamento());
            statement.setTime(3, agendamento.getHoraAgendamento());
            statement.executeUpdate();
        }
    }

    public void atualizarAgendamento(Agendamento agendamento) throws SQLException {
        String sql = "UPDATE agendamento SET hora_agendamento = ? WHERE email_cliente = ? AND data_agendamento = ?";
        try (Connection connection = helperDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setTime(1, agendamento.getHoraAgendamento());
            statement.setString(2, agendamento.getEmailCliente());
            statement.setDate(3, agendamento.getDataAgendamento());
            statement.executeUpdate();
        }
    }

    public void excluirAgendamento(Agendamento agendamento) throws SQLException {
        ServicosAgendadosDAO servicosAgendadosDAO = new ServicosAgendadosDAO(helperDAO);

  
        servicosAgendadosDAO.removerServicosAgendadosPorAgendamento(agendamento);

        String sql = "DELETE FROM agendamento WHERE email_cliente = ? AND data_agendamento = ?";
        try (Connection connection = helperDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, agendamento.getEmailCliente());
            statement.setDate(2, agendamento.getDataAgendamento());
            statement.executeUpdate();
            
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Agendamento obterAgendamento(String emailCliente, Date dataAgendamento) throws SQLException {
        String sql = "SELECT * FROM agendamento WHERE email_cliente = ? AND data_agendamento = ?";
        try (Connection connection = helperDAO.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, emailCliente);
            statement.setDate(2, dataAgendamento);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Agendamento agendamento = new Agendamento();
                    agendamento.setEmailCliente(resultSet.getString("email_cliente"));
                    agendamento.setDataAgendamento(resultSet.getDate("data_agendamento"));
                    agendamento.setHoraAgendamento(resultSet.getTime("hora_agendamento"));
                    return agendamento;
                }
            }
        }
        return null;
    }

    public List<Agendamento> obterTodosAgendamentos(String filtro) throws SQLException {
        List<Agendamento> agendamentos = new ArrayList<>();
        String query = "SELECT * FROM agendamento";

        if (filtro != null && !filtro.isEmpty()) {
            query += " WHERE email_cliente LIKE ?";
        }

        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            if (filtro != null && !filtro.isEmpty()) {
                statement.setString(1, "%" + filtro + "%");
            }

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Agendamento agendamento = new Agendamento(
                            resultSet.getString("email_cliente"),
                            resultSet.getDate("data_agendamento"),
                            resultSet.getTime("hora_agendamento")
                    );
                    agendamentos.add(agendamento);
                }
            }
        }
        return agendamentos;
    }
}
