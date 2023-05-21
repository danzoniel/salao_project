/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.salaoproject;


import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
    private Label formatoInvalidoLabel;

    @FXML
    private Label senhaIncorretaLabel;

    @FXML
    private Label showPasswordButton;


    @FXML
    private TextField passwordFieldView;


    @FXML
    public void initialize() {
        System.setProperty("URLDB", "jdbc:mysql://localhost:3306/salao_db");
        System.setProperty("USERDB", "myuser");
        System.setProperty("PASSDB", "mypassword");
        System.setProperty("TABELADB", "default");

        // loginButton.disableProperty().bind(usernameField.textProperty().isEmpty().or(passwordField.textProperty().isEmpty()));
        // formatoInvalidoLabel.setVisible(false);
        // senhaIncorretaLabel.setVisible(false);
    }

    private UsuarioDAO usuarioDAO;

    public void LoadScene(String fileName) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        
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

    public LoginController() {
        // HelperDAO helperDAO = new HelperDAO(System.getProperty("URLDB"), System.getProperty("USERDB"), System.getProperty("PASSDB"));
        HelperDAO helperDAO = new HelperDAO("jdbc:mysql://localhost:3306/salao_db", "myuser", "mypassword");
        usuarioDAO = new UsuarioDAO(helperDAO);
    }


    @FXML
    void handleLogin(MouseEvent event) throws SQLException, IOException {
        formatoInvalidoLabel.setVisible(false);
        senhaIncorretaLabel.setVisible(false);
        boolean validaLogin;
        validaLogin = validarUsuarioSenha(usernameField.getText(), passwordField.getText());
        if (validaLogin == true) {
            ScreenChanger.changeScreen(forgottenPassButton, "tela_dash.fxml");
        } else {
            System.out.println("ocorreu um erro durante o processo de login");
        }
    }

    // public String criaCondition(String nomeColuna, Object condition) {
    //     if (condition instanceof String) {
    //     return nomeColuna + " ='" + condition + "'";
    //     } 
    //     return nomeColuna + " = " + condition;
    // }

    boolean viewStatus = false;

    @FXML
    void checkPassField(KeyEvent  event) {
        if (passwordField.getText() != "") {
            showPasswordButton.setDisable(false);
        } else {
            showPasswordButton.setDisable(true);
        }
        if (viewStatus == true) {
            passwordField.setText(passwordFieldView.getText());
        }
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

    public boolean validarUsuarioSenha(String email, String senha) throws SQLException, IOException {
        if (!validarEmail(email)) {
            formatoInvalidoLabel.setVisible(true);
            formatoInvalidoLabel.setText("Email inválido. Informe um email válido.");
            System.out.println("formato de email inválido");
            return false;
        }

        Usuario usuario = null;

        try {
            usuario = usuarioDAO.getUsuario(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (usuario == null) {
            senhaIncorretaLabel.setVisible(true);
            senhaIncorretaLabel.setText("Usuário ou senha incorreta.");
            System.out.println("Usuário não encontrado.");
            return false;
        }

        if (!usuario.getSenha().equals(senha)) {
            senhaIncorretaLabel.setVisible(true);
            senhaIncorretaLabel.setText("Usuário ou senha incorreta.");
            System.out.println("Senha incorreta.");
            return false;
        }

        System.out.println("Login realizado com sucesso.");
        return true;
    }


    @FXML
    void showPass(MouseEvent event) {
        if (viewStatus == false) {
            viewStatus = true;
            String password = passwordField.getText();
            passwordField.setVisible(false);
            passwordFieldView.setText(password);
            passwordFieldView.setVisible(true);
        } else {
            viewStatus = false;
            String password = passwordFieldView.getText();
            passwordFieldView.setVisible(false);
            passwordField.setText(password);
            passwordField.setVisible(true);
            usernameField.setFocusTraversable(false);
            passwordField.setFocusTraversable(true);
        }
    }
    
}

