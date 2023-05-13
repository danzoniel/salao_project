package com.mycompany.salaoproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        TextField textField = new TextField();
        // textField.
        // textField.getStyleClass().add("text-field");
        textField.setPromptText("usuário");

        Image icon = new Image("https://www.flaticon.com/br/sticker-gratis/vaca_10776585?related_id=10776585");
        ImageView imageView = new ImageView(icon);

        // Using the ImageView with a Label
        Label labelIcon = new Label("labelIcon", imageView);

        VBox root = new VBox(loadFXML("telalogin"), labelIcon);

        stage.setTitle("Login");
        scene = new Scene(root, 640, 426);
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