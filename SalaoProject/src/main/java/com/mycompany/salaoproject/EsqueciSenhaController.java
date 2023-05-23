package com.mycompany.salaoproject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EsqueciSenhaController {

    @FXML
    private Label backToLoginButton;

    
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button alterarButton;

    @FXML
    public void initialize() {
        alterarButton.disableProperty().bind(usernameField.textProperty().isEmpty().or(passwordField.textProperty().isEmpty()));
    }

    @FXML
    void handleBackToLogin(MouseEvent event) throws IOException{

    FXMLLoader loader = new FXMLLoader(getClass().getResource("telalogin.fxml"));
    
    Parent newSceneRoot;
    try {
        newSceneRoot = loader.load();
    } catch (IOException e) {
        e.printStackTrace();
        return;
    }

    Scene currentScene = backToLoginButton.getScene();
    Scene newScene = new Scene(newSceneRoot, currentScene.getWidth(), currentScene.getHeight());

    Stage currentStage = (Stage) currentScene.getWindow();

    currentStage.setScene(newScene);
    
    }

   
    
}
