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


    // método para atualizar um usuário existente no banco de dados
    // public void updateUsuario(Usuario usuario) throws SQLException {
    //     String query = "UPDATE usuario SET nome=?, usuario=?, senha=?, email=? WHERE id=?";
    //     try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
    //         statement.setString(1, usuario.getNome());
    //         statement.setString(2, usuario.getUsuario());
    //         statement.setString(3, usuario.getSenha());
    //         statement.setString(4, usuario.getEmail());
    //         statement.setInt(5, usuario.getId());
    //         statement.executeUpdate();
    //     }
    // }

    // método para excluir um usuário do banco de dados
    // public void deleteUsuario(int usuarioId) throws SQLException {
    //     String query = "DELETE FROM usuario WHERE id=?";
    //     try (PreparedStatement statement = helperDAO.getConnection().prepareStatement(query)) {
    //         statement.setInt(1, usuarioId);
    //         statement.executeUpdate();
    //     }
    // }

    // método para obter um usuário específico do banco de dados
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


    // método para obter todos os usuários do banco de dados
    public List<Usuario> getUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";
        try (Statement statement = helperDAO.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                    resultSet.getString("email"),
                    resultSet.getString("senha")
                );
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
}