package com.mycompany.salaoproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;



public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    
        Image icon = new Image("https://www.pinclipart.com/picdir/middle/187-1874171_scars-clipart.png");

        int iconWidth = 64;
        int iconHeight = 64;
        Image resizedIcon = new Image(icon.getUrl(), iconWidth, iconHeight, true, true);
        stage.getIcons().add(resizedIcon);

        Scene scene = new Scene(loadFXML("telalogin"), 850, 550);
        stage.setTitle("Contaleiro");
        stage.setScene(scene);
        stage.show();
    }
    
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        System.out.println(fxml);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("telalogin.fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }
}