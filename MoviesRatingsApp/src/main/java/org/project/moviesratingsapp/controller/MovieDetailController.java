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

/**
 * Controller class for the Movie Detail View.
 * The class has the following attributes:
 * title: the Text object that displays the movie's title
 * director: the TextField object that displays the movie's director
 * genre: the TextField object that displays the movie's genre
 * language: the TextField object that displays the movie's language
 * country: the TextField object that displays the movie's country
 * runtime: the TextField object that displays the movie's runtime
 * imdbRating: the TextField object that displays the movie's IMDb rating
 * metascore: the TextField object that displays the movie's Metascore
 * */

public class MovieDetailController {

    /** the Text object that displays the movie's title */
    @FXML
    private Text title;

    /** the TextField object that displays the movie's director */
    @FXML
    private TextField director;

    /** the TextField object that displays the movie's genre */
    @FXML
    private TextField genre;

    /** the TextField object that displays the movie's language */
    @FXML
    private TextField language;

    /** the TextField object that displays the movie's country */
    @FXML
    private TextField country;

    /** the TextField object that displays the movie's runtime */
    @FXML
    private TextField runtime;

    /** the TextField object that displays the movie's IMDb rating */
    @FXML
    private TextField imdbRating;

    /** the TextField object that displays the movie's Metascore */
    @FXML
    private TextField metascore;

    /**
     * Method that fills the TextFields with the data from the FullMovie object.
     * The method takes a FullMovie object as a parameter and fills the TextFields with the respective data.
     * @param fullMovie the FullMovie object that contains the data to be displayed
     */
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
