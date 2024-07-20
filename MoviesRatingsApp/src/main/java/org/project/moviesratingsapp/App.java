package org.project.moviesratingsapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The main class of the application.<br>
 * The class extends the Application class and overrides the start method.
 */
public class App extends Application {

    /**
     * The start method loads the FXML file and sets the scene.
     * @param stage the Stage object that contains the scene
     * @throws IOException if the FXML file is not found
     * */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("movies-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Movies Ratings App");
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/icon.png"))));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}