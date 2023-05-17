/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.salaoproject;


import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.mycompany.salaoproject.DAO.FunctionDAO;
import com.mycompany.salaoproject.DAO.HelperDAO;
import com.mycompany.salaoproject.DAO.UsuarioDAO;
import com.mycompany.salaoproject.models.Usuario;


public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label forgottenPassButton;

    @FXML
    private Label titleLabel;

    @FXML
    private Button loginButton;

    @FXML
    public void initialize() {
        loginButton.disableProperty().bind(usernameField.textProperty().isEmpty().or(passwordField.textProperty().isEmpty()));
    }

    private UsuarioDAO usuarioDAO;

    public LoginController() {
        HelperDAO helperDAO = new HelperDAO(System.getProperty("URLDB"), System.getProperty("USERDB"), System.getProperty("PASSDB"));
        usuarioDAO = new UsuarioDAO(helperDAO);
    }


    @FXML
    void handleLogin(MouseEvent event) throws SQLException {
        validarUsuarioSenha(usernameField.getText(), passwordField.getText());  
    }

    public String criaCondition(String nomeColuna, Object condition) {
        if (condition instanceof String) {
        return nomeColuna + " ='" + condition + "'";
        } 
        return nomeColuna + " = " + condition;
    }

    @FXML
    void handleForgottenPass(MouseEvent event) throws IOException{
    FXMLLoader loader = new FXMLLoader(getClass().getResource("esqueci_senha.fxml"));
    
    Parent newSceneRoot;
    try {
        newSceneRoot = loader.load();
    } catch (IOException e) {
        e.printStackTrace();
        return;
    }

    Scene currentScene = forgottenPassButton.getScene();
    Scene newScene = new Scene(newSceneRoot, currentScene.getWidth(), currentScene.getHeight());

    Stage currentStage = (Stage) currentScene.getWindow();

    currentStage.setScene(newScene);

    }

    private boolean validarEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validarUsuarioSenha(String email, String senha) throws SQLException {
        // Verificar se o email está em um formato válido
        if (!validarEmail(email)) {
            System.out.println("Email inválido. Informe um email válido.");
            return false;
        }

        Usuario usuario = null;

        try {
            usuario = usuarioDAO.getUsuario(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return false;
        }

        // Verificar se a senha está correta
        if (!usuario.getSenha().equals(senha)) {
            System.out.println("Usuário ou senha incorreta.");
            return false;
        }

        // Usuário e senha válidos
        System.out.println("Login realizado com sucesso.");
        return true;
    }
}