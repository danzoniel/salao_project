package com.mycompany.salaoproject.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mycompany.salaoproject.models.Comparecimento;
import com.mycompany.salaoproject.models.Despesa;
import com.mycompany.salaoproject.models.FluxoCaixa;


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

    public void addFluxoCaixaDespesa(Despesa despesa) throws SQLException {
        String query = "INSERT Into fluxo_caixa(id_despesa, id_comparecimento, email, valor_movimentado_saida, valor_movimentado_entrada, data_movimentacao) values (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setInt(1, despesa.getIdDespesa());
            statement.setString(2, null);
            statement.setString(3, null);
            statement.setDouble(4, despesa.getPrecoUnitario()*despesa.getQuantidade());
            statement.setDouble(5, 0);
            statement.setString(6, despesa.getData());
            statement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    
    }

    public void addFluxoCaixaComparecimento(Comparecimento comparecimento, double valorMovimentadoEntrada, String dataMovimentacao) throws SQLException {
        String query = "INSERT INTO fluxo_caixa (id_despesa, id_comparecimento, email, valor_movimentado_saida, valor_movimentado_entrada, data_movimentacao) VALUES (NULL, ?, ?, 0, ?, ?)";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setInt(1, comparecimento.getId_comparecimento());
            statement.setString(2, comparecimento.getEmail_cliente());
            statement.setDouble(3, valorMovimentadoEntrada);
            statement.setString(4, dataMovimentacao);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirFluxoCaixa(FluxoCaixa fluxoCaixa) throws SQLException {
        String query = "INSERT INTO fluxo_caixa (id_despesa, id_comparecimento, email, valor_movimentado_saida, valor_movimentado_entrada, data_movimentacao) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setString(1, null);
            statement.setInt(2, fluxoCaixa.getIdComparecimento());
            statement.setString(3, fluxoCaixa.getEmail());
            statement.setDouble(4, 0);
            statement.setDouble(5, fluxoCaixa.getValorMovimentadoEntrada());
            statement.setString(6, fluxoCaixa.getDataMovimentacao().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
