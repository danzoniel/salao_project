package com.mycompany.salaoproject.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FluxoCaixaDAO {
    
    private HelperDAO helperDAO;

    public FluxoCaixaDAO(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
    }

    public ResultSet getMonthlyProfit() throws SQLException {
        String query = "SELECT YEAR(data_movimentacao) AS year, " +
        "MONTH(data_movimentacao) AS month, " +
        "SUM(valor_movimentado_entrada) - SUM(valor_movimentado_saida) AS profit " +
        "FROM fluxo_caixa " +
        "GROUP BY YEAR(data_movimentacao), MONTH(data_movimentacao) " +
        "ORDER BY YEAR(data_movimentacao), MONTH(data_movimentacao)";

        PreparedStatement statement = helperDAO.getConnection().prepareStatement(query);
        return statement.executeQuery();
    }

    public ResultSet getTotalProfit() throws SQLException {
        String query = "SELECT SUM(valor_movimentado_entrada) - SUM(valor_movimentado_saida) AS lucro_total FROM fluxo_caixa";

        PreparedStatement statement = helperDAO.getConnection().prepareStatement(query);
        return statement.executeQuery();
    }
}
