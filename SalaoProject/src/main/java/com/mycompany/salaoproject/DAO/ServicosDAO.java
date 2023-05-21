package com.mycompany.salaoproject.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.salaoproject.models.Servicos;

public class ServicosDAO {

    private HelperDAO helperDAO;

    public ServicosDAO(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
    }
    
    public void excluirServico(Servicos servico) throws SQLException {
        String query = "DELETE FROM servicos_disponiveis WHERE id_servico = ?";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setInt(1, servico.getIdServico());
            statement.executeUpdate();
        }
    }

    public List<Servicos> getServicos(String filtro) throws SQLException {
        List<Servicos> servicos = new ArrayList<>();
    String query = "SELECT * FROM servicos_disponiveis";
    
    if (filtro != null && !filtro.isEmpty()) {
        query += " WHERE descricao_servico LIKE ?";
    }
    
    try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
        if (filtro != null && !filtro.isEmpty()) {
            statement.setString(1, "%" + filtro + "%");
        }

        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Servicos servico = new Servicos(
                        resultSet.getInt("id_servico"),
                        resultSet.getString("descricao_servico"),
                        resultSet.getDouble("preco")
                );
                servicos.add(servico);
            }
        }
    }
    return servicos;
}
    
}
