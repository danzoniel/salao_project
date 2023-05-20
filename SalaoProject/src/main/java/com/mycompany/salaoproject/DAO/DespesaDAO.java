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

    public List<Despesa> getDespesas() throws SQLException {
        List<Despesa> despesas = new ArrayList<>();
        String query = "SELECT * FROM despesa";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Despesa despesa = new Despesa(
                    resultSet.getInt("id_despesa"),
                    resultSet.getString("produto_descricao"),
                    resultSet.getDouble("preco_unitario"),
                    resultSet.getInt("quantidade"),
                    resultSet.getTimestamp("data_saida")
                );
                despesas.add(despesa);
            }
        }
        return despesas;
    }
}
