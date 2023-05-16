package com.mycompany.salaoproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    
        Image icon = new Image("https://www.pinclipart.com/picdir/middle/187-1874171_scars-clipart.png"); // Replace "icon.png" with the path to your icon image file

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
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        // try {
        //     DBconnection.getConnection();
        //     System.out.println("database connected succefully");
        // } catch (ClassNotFoundException | SQLException ex) {
        //     Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        // }
        launch();
    }
}