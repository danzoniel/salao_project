package com.mycompany.salaoproject;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ScreenChanger {
    public static void changeScreen(Label button, String fxmlFileName) {
        if (button != null && button.getScene() != null) {
            FXMLLoader loader = new FXMLLoader(ScreenChanger.class.getResource(fxmlFileName));

            Parent newSceneRoot;
            try {
                newSceneRoot = loader.load();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            Scene currentScene = button.getScene();
            Scene newScene = new Scene(newSceneRoot, currentScene.getWidth(), currentScene.getHeight());

            Stage currentStage = (Stage) currentScene.getWindow();
            currentStage.setScene(newScene);
        }
    }
}
