/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.salaoproject.DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.salaoproject.models.Usuario;

public class UsuarioDAO {
    private HelperDAO helperDAO;

    public UsuarioDAO(HelperDAO helperDAO) {
        this.helperDAO = helperDAO;
    }


    // método para adicionar um usuário ao banco de dados
    // public void addUsuario(Usuario usuario) throws SQLException {
    //     String query = "INSERT INTO usuario (email, senha) VALUES (?, ?)";
    //     try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
    //         statement.setString(4, usuario.getEmail());
    //         statement.setString(3, usuario.getSenha());
    //         statement.executeUpdate();
    //     }
    // }


    public void updateUsuario(Usuario usuario) throws SQLException {
        String query = "UPDATE usuario_admin SET senha=? WHERE email=?";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setString(1, usuario.getSenha());
            statement.setString(2, usuario.getEmail());
            statement.executeUpdate();
        }
    }

    public void excluirUsuario(Usuario usuario) throws SQLException {
        String query = "DELETE FROM usuario_admin WHERE email=?";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setString(1, usuario.getEmail());
            statement.executeUpdate();
        }
    }

    public Usuario getUsuario(String usuarioEmail) throws SQLException {
        Usuario usuario = null;
        String query = "SELECT * FROM usuario_admin WHERE email = ?";
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            statement.setString(1, usuarioEmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                usuario = new Usuario(
                    resultSet.getString("email"),
                    resultSet.getString("senha")
                );
            }
        }
        return usuario;
    }


    public List<Usuario> getUsuarios(String filtro) throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario_admin";
    
        if (filtro != null && !filtro.isEmpty()) {
            query += " WHERE email LIKE ?";
        }
    
        try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
            if (filtro != null && !filtro.isEmpty()) {
                statement.setString(1, "%" + filtro + "%");
            }
    
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Usuario usuario = new Usuario(
                            resultSet.getString("email"),
                            resultSet.getString("senha")
                    );
                    usuarios.add(usuario);
                }
            }
        }
        return usuarios;
    }
    
}