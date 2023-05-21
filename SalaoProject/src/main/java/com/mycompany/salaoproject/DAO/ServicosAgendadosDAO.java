package com.mycompany.salaoproject.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public boolean existeServicoAgendado(int idServico) throws SQLException {
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
        return false;
    }
    
}
