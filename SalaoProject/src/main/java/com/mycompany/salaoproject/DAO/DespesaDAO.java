package com.mycompany.salaoproject.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.salaoproject.models.Despesa;

public class DespesaDAO {

    private HelperDAO helperDAO;

    public DespesaDAO(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
    }

    public List<Despesa> getDespesas(String filtro) throws SQLException {
        List<Despesa> despesas = new ArrayList<>();
        String query = "SELECT * FROM despesa";

        if (filtro != null && !filtro.isEmpty()) {
            query += " WHERE produto_descricao LIKE ?";
        }

        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            if (filtro != null && !filtro.isEmpty()) {
                statement.setString(1, "%" + filtro + "%");
            }   

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Despesa despesa = new Despesa(
                        resultSet.getInt("id_despesa"),
                        resultSet.getString("produto_descricao"),
                        resultSet.getDouble("preco_unitario"),
                        resultSet.getInt("quantidade"),
                        resultSet.getTimestamp("data_saida")
                    );
                    despesas.add(despesa);
            }   }
        }
        return despesas;
    }

    public void excluirDespesa(Despesa despesa) throws SQLException {
        String query = "DELETE FROM despesa WHERE id_despesa=?";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setInt(1, despesa.getIdDespesa());
            statement.executeUpdate();
        }
    }

    public void addDespesa(Despesa despesa) throws SQLException {
        String query = "INSERT INTO despesa (produto_descricao, preco_unitario, quantidade, data_saida) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setString(1, despesa.getProdutoDescricao());
            statement.setDouble(2, despesa.getPrecoUnitario());
            statement.setInt(3, despesa.getQuantidade());
            statement.setObject(4, despesa.getData());
            statement.executeUpdate();
        }
    } 

    public int getNextId() throws SQLException {
        String query = "SELECT MAX(id_despesa) FROM despesa";
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