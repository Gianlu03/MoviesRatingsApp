package org.example.moviesratingsapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        /* API key: feb7be56 */

        /* setting up the object that will handle the HttpRequest */
        // HttpHandler handler = new HttpHandler("feb7be56");

        /* setting up the HttpRequest */
        // Movie movieData = new Movie(handler.request());

        /**
         * Resources:
         * Switch scene: https://www.youtube.com/watch?v=hcM-R-YOKkQ
         * FilteredList: https://www.youtube.com/watch?v=3eR6csDU438&ab_channel=Chewy

         */

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("movies-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 610);
        stage.setTitle("Movies Ratings App");
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/icon.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}