package com.mycompany.salaoproject.DAO;

/**
 *
 * @author danie
 */
import java.sql.*;

public class HelperDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    private static HelperDAO instance;

    public HelperDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    public static HelperDAO getInstance() {
        if (instance == null) {
            String jdbcURL = "jdbc:mysql://localhost:3306/salao_db";
            String jdbcUsername = "myuser";
            String jdbcPassword = "mypassword";
            
            instance = new HelperDAO(jdbcURL, jdbcUsername, jdbcPassword);
        }
        return instance;
    }

    public void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    public void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    public Connection getConnection() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            connect();
        }
        return jdbcConnection;
    }
}
