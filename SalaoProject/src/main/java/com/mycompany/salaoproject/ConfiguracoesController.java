package com.mycompany.salaoproject;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ConfiguracoesController {

    @FXML
    private AnchorPane apServicos;
    
    @FXML
    void enableApServicos(MouseEvent event) {
        System.out.println("entrou");
        apServicos.setDisable(false);
    }

    @FXML
    void handleApServicos(MouseEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("cadastro_servico.fxml"));
        
        Parent newSceneRoot;
        try {
            newSceneRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene currentScene = apServicos.getScene();
        Scene newScene = new Scene(newSceneRoot, currentScene.getWidth(), currentScene.getHeight());

        Stage currentStage = (Stage) currentScene.getWindow();

        currentStage.setScene(newScene);
    }
        
}
