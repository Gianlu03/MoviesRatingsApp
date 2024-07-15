package org.project.moviesratingsapp.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.project.moviesratingsapp.model.FullMovie;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieDetailController {

    @FXML
    private Text title;

    @FXML
    private TextField director;

    @FXML
    private TextField genre;

    @FXML
    private TextField language;

    @FXML
    private TextField country;

    @FXML
    private TextField runtime;

    @FXML
    private TextField imdbRating;

    @FXML
    private TextField metascore;

    @FXML
    public void fill(FullMovie fullMovie) {
        this.title.setText(fullMovie.getTitle());
        this.director.setText(fullMovie.getDirector());
        this.genre.setText(fullMovie.getGenre());
        this.language.setText(fullMovie.getLanguage());
        this.country.setText(fullMovie.getCountry());
        this.runtime.setText(fullMovie.getRuntime());
        this.imdbRating.setText(fullMovie.getImdbRating());
        this.metascore.setText(fullMovie.getMetascore());
    }


}
