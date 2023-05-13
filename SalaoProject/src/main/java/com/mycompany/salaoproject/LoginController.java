/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package com.mycompany.salaoproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println(username);
        System.out.println(usernameField.getText());


        if (username.equals("admin") && password.equals("admin")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login");
            alert.setHeaderText(null);
            alert.setContentText("Login successful!");
            alert.showAndWait();
        } else {
            // login failed, show error message
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Invalid username or password.");
            alert.showAndWait();
        }
    }
}