package com.mycompany.salaoproject.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.salaoproject.models.Agendamento;

public class ServicosAgendadosDAO {

    private HelperDAO helperDAO;

    public ServicosAgendadosDAO(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
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
