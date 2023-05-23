package com.mycompany.salaoproject.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.salaoproject.models.Agendamento;
import com.mycompany.salaoproject.models.Servicos;

public class ServicosAgendadosDAO {

    private HelperDAO helperDAO;

    public ServicosAgendadosDAO(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
    }


    public void addServicoAgendado(Servicos servicos, Agendamento agendamento) throws SQLException {
        String query = "INSERT INTO servicos_agendados (id_servico, email_cliente, data_agendamento) VALUES (?, ?, ?)";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setInt(1, servicos.getIdServico());
            statement.setString(2, agendamento.getEmailCliente());
            statement.setDate(3, agendamento.getDataAgendamento());
            statement.executeUpdate();
        }
    }

    public void removerServicosAgendadosPorIdServico(int idServico) throws SQLException {
        String query = "DELETE FROM servicos_agendados WHERE id_servico = ?";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setInt(1, idServico);
            statement.executeUpdate();
        }
    }

    public void removerServicosAgendadosPorAgendamento(Agendamento agendamento) throws SQLException {
        String query = "DELETE FROM servicos_agendados WHERE email_cliente = ? AND data_agendamento = ?";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setString(1, agendamento.getEmailCliente());
            statement.setDate(2, agendamento.getDataAgendamento());
            statement.executeUpdate();
            System.out.println("excluido com sucesso");
        } 
        catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean existeServicoAgendado(int idServico, Agendamento agendamento) throws SQLException {
        
        if (idServico != 0) {
            String query = "SELECT COUNT(*) FROM servicos_agendados WHERE id_servico = ?";
            try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
                statement.setInt(1, idServico);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int count = resultSet.getInt(1);
                        return count > 0;
                    }
                }
            }
        } else {
            String query = "SELECT COUNT(*) FROM servicos_agendados WHERE email_cliente = ? AND data_agendamento = ?";
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
        }
        
        return false;
    }
    
}
