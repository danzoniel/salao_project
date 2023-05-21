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

public void cadastrarServico(Servicos servico) throws SQLException {
    String query = "INSERT INTO servicos_disponiveis (descricao_servico, preco) VALUES (?, ?)";
    try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
        statement.setString(1, servico.getServico());
        statement.setDouble(2, servico.getPreco());
        statement.executeUpdate();

        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                servico.setIdServico(generatedId);
            } else {
                throw new SQLException("Falha ao obter o ID gerado para o serviço.");
            }
        }
    }
}

public int getNextId() throws SQLException {
    String query = "SELECT MAX(id_servico) FROM servicos_disponiveis";
    try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
            int maxId = resultSet.getInt(1);
            return maxId + 1;
        } else {
            return 1;
        }
    }
}
    
}
