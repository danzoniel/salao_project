package com.mycompany.salaoproject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class PrimaryController {
    
    
    /*    @FXML
    private void switchToSecondary() throws IOException {
    App.setRoot("secondary");
    }*/
    
    @FXML
    private Button loginButton;
     
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    /*    @FXML
    public void initialize() {
    
    String username = usernameField.getText();
    String password = passwordField.getText();
    System.out.println(username);
    System.out.println(usernameField.getText());
    // define o evento para o botão de login
    LoginController loginController = new LoginController();
    loginButton.setOnAction(event -> loginController.handleLogin(event));
    }*/
    
    @FXML
    public void callLoginHandler() {
         // define o evento para o botão de login
        LoginController loginController = new LoginController();
        loginButton.setOnAction(event -> loginController.handleLogin(event));
    }
}
