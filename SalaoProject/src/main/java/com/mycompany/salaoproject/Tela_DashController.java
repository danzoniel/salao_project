package com.mycompany.salaoproject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Tela_DashController {

    @FXML
    private Label btnSair;

    @FXML
    private Label btnConfigs;

    // public void LoadScene(String fileName) throws IOException{
    //     FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
        
    //     Parent newSceneRoot;
    //     try {
    //         newSceneRoot = loader.load();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //         return;
    //     }

    //     Scene currentScene = forgottenPassButton.getScene();
    //     Scene newScene = new Scene(newSceneRoot, currentScene.getWidth(), currentScene.getHeight());

    //     Stage currentStage = (Stage) currentScene.getWindow();

    //     currentStage.setScene(newScene);
    // }

    @FXML
    void handleBtnSair(MouseEvent event) {
        if (btnSair != null && btnSair.getScene() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("telalogin.fxml"));
        
            Parent newSceneRoot;
            try {
                newSceneRoot = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            Scene currentScene = btnSair.getScene();
            Scene newScene = new Scene(newSceneRoot, currentScene.getWidth(), currentScene.getHeight());

            Stage currentStage = (Stage) currentScene.getWindow();

            currentStage.setScene(newScene);
        }
       
    }

    @FXML
    void handleBtnConfigs(MouseEvent event) {
        if (btnConfigs != null && btnConfigs.getScene() != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("configuracoes.fxml"));
        
            Parent newSceneRoot;
            try {
                newSceneRoot = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            Scene currentScene = btnConfigs.getScene();
            Scene newScene = new Scene(newSceneRoot, currentScene.getWidth(), currentScene.getHeight());

            Stage currentStage = (Stage) currentScene.getWindow();

            currentStage.setScene(newScene);
        }
       
    }
    
}
