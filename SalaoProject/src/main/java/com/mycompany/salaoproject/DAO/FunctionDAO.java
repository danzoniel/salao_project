package com.mycompany.salaoproject.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FunctionDAO {

    private HelperDAO helperDAO;
    
    public FunctionDAO(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
    }

    public void addIntoBD(String[] columns, Object[] values) throws SQLException {
        StringBuilder columnNames = new StringBuilder();
        StringBuilder valuePlaceholders = new StringBuilder();

        for (int i = 0; i < columns.length; i++) {
            columnNames.append(columns[i]);
            valuePlaceholders.append("?");

            if (i < columns.length - 1) {
                columnNames.append(", ");
                valuePlaceholders.append(", ");
            }
        }

        String query = "INSERT INTO " + System.getProperty("TABELADB") + " (" + columnNames + ") VALUES (" + valuePlaceholders + ")";

        try (PreparedStatement statement =  helperDAO.getConnection().prepareStatement(query)) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof String) {
                    statement.setString(i + 1, (String) values[i]);
                } else if (values[i] instanceof Integer) {
                    statement.setInt(i + 1, (int) values[i]);
                }
            }

            System.out.println("Executing SQL query: " + statement.toString());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDataDb(String[] columns, Object[] values, String condition) throws SQLException {

        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append("UPDATE ").append(System.getProperty("TABELADB")).append(" SET ");
    
        for (int i = 0; i < columns.length; i++) {
            updateQuery.append(columns[i]).append(" = ?");
    
            if (i < columns.length - 1) {
                updateQuery.append(", ");
            }
        }
    
        updateQuery.append(" WHERE ").append(condition);
    
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(updateQuery.toString())) {
            for (int i = 0; i < values.length; i++) {
                if (values[i] instanceof String) {
                    statement.setString(i + 1, (String) values[i]);
                } else if (values[i] instanceof Integer) {
                    statement.setInt(i + 1, (int) values[i]);
                } 
            }
            System.out.println("Update query: " + updateQuery);
            
            int rowsAffected = statement.executeUpdate();

            System.out.println("Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDb(String condition) throws SQLException {
        String query = "DELETE FROM " + System.getProperty("TABELADB") + " WHERE " + condition;

        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.executeUpdate();
            System.out.println("Dados excluídos com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // public Object[][] getDataDb(String[] columns, String condition) throws SQLException {
    //     StringBuilder columnNames = new StringBuilder();

    //     for (int i = 0; i < columns.length; i++) {
    //         columnNames.append(columns[i]);
    
    //         if (i < columns.length - 1) {
    //             columnNames.append(", ");
    //         }
    //     }
    
    //     String query = "SELECT " + columnNames + " FROM " + System.getProperty("TABELADB") + " WHERE " + condition;
    
    //     try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
    //         ResultSet resultSet = statement.executeQuery();
    
    //         List<Object[]> results = new ArrayList<>();
    
    //         while (resultSet.next()) {
    //             Object[] row = new Object[columns.length];
    //             for (int i = 0; i < columns.length; i++) {
    //                 row[i] = resultSet.getObject(columns[i]);
    //             }
    //             results.add(row);
    //         }
    
    //         return results.toArray(new Object[0][]);
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    
    //     return null;
    // }

    // public void getAllDataDb(String[] columns) throws SQLException {
    //     StringBuilder columnNames = new StringBuilder();
    
    //     for (int i = 0; i < columns.length; i++) {
    //         columnNames.append(columns[i]);
    
    //         if (i < columns.length - 1) {
    //             columnNames.append(", ");
    //         }
    //     }
    
    //     String query = "SELECT " + columnNames + " FROM " + System.getProperty("TABELADB");
    
    //     try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
    //         ResultSet resultSet = statement.executeQuery();
    
    //         while (resultSet.next()) {
    //             for (String column : columns) {
    //                 String value = resultSet.getString(column);
    //                 System.out.println(column + ": " + value);
    //             }
    //             System.out.println("--------------------");
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
}
