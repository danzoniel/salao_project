package com.mycompany.salaoproject;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
    
        // AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("telalogin.fxml"));
    
        // Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    
        // stage.setWidth(screenBounds.getWidth());
        // stage.setHeight(screenBounds.getHeight());

        Scene scene = new Scene(loadFXML("telalogin"), 850, 550);
        stage.setTitle("Login");
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
        try {
            DBconnection.getConnection();
            System.out.println("database connected succefully");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        launch();
    }
}