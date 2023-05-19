package com.mycompany.salaoproject;

import java.io.IOException;
import java.net.URL;
import java.util.ListResourceBundle;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private Label btnSair;

    @FXML
    private Label btnDash;

    @FXML
    private Label btnConfig;

    @FXML
    private Label btnHistAtend;

    @FXML
    private Label btnHistDesp;


    @FXML
    void handleSair(MouseEvent event) {
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

    @FXML
    void handleDash(MouseEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("tela_dash.fxml"));
        
        Parent newSceneRoot;
        try {
            newSceneRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene currentScene = btnDash.getScene();
        Scene newScene = new Scene(newSceneRoot, currentScene.getWidth(), currentScene.getHeight());

        Stage currentStage = (Stage) currentScene.getWindow();

        currentStage.setScene(newScene);
    }

    @FXML
    void handleConfig(MouseEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("configuracoes.fxml"));
        
        Parent newSceneRoot;
        try {
            newSceneRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene currentScene = btnConfig.getScene();
        Scene newScene = new Scene(newSceneRoot, currentScene.getWidth(), currentScene.getHeight());

        Stage currentStage = (Stage) currentScene.getWindow();

        currentStage.setScene(newScene);
    }

    @FXML
    void handleHistAtend(MouseEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("telalogin.fxml"));
        
        Parent newSceneRoot;
        try {
            newSceneRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene currentScene = btnHistAtend.getScene();
        Scene newScene = new Scene(newSceneRoot, currentScene.getWidth(), currentScene.getHeight());

        Stage currentStage = (Stage) currentScene.getWindow();

        currentStage.setScene(newScene);
    }

    @FXML
    void handleHistDesp(MouseEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("telalogin.fxml"));
        
        Parent newSceneRoot;
        try {
            newSceneRoot = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Scene currentScene = btnHistDesp.getScene();
        Scene newScene = new Scene(newSceneRoot, currentScene.getWidth(), currentScene.getHeight());

        Stage currentStage = (Stage) currentScene.getWindow();

        currentStage.setScene(newScene);
    }
}
