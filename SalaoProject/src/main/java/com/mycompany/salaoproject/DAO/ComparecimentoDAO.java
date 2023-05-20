package com.mycompany.salaoproject.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.salaoproject.models.Comparecimento;


public class ComparecimentoDAO {

    private HelperDAO helperDAO;

    public ComparecimentoDAO(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
    }

    public Comparecimento getComparecimento(int id_comparecimento) throws SQLException {
        Comparecimento comparecimento = null;
        String query = "SELECT * FROM comparecimento WHERE id_comparecimento = ?";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setInt(1, id_comparecimento);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                comparecimento = new Comparecimento(
                    resultSet.getInt("id_comparecimento"),
                    resultSet.getInt("id_agendamento"),
                    resultSet.getString("email_cliente"),
                    resultSet.getTimestamp("data_agendamento")
                );
            }
        }
        return comparecimento;
    }

    public List<Comparecimento> getComparecimentos() throws SQLException {
        List<Comparecimento> comparecimentos = new ArrayList<>();
        String query = "SELECT * FROM comparecimento";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Comparecimento comparecimento = new Comparecimento(
                    resultSet.getInt("id_comparecimento"),
                    resultSet.getInt("id_agendamento"),
                    resultSet.getString("email_cliente"),
                    resultSet.getTimestamp("data_agendamento")
                );
                comparecimentos.add(comparecimento);
            }
        }
        return comparecimentos;
    }
    
}
