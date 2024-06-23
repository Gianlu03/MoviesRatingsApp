package org.example.moviesratingsapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.moviesratingsapp.HttpHandler;
import org.example.moviesratingsapp.model.Movie;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieController implements Initializable {

    /**
     * Controller class for the TableView that displays the movies' data.
     * Each column in the TableView has an ID which refers to the respective
     * attribute in the MovieController class.
     */

    @FXML
    private TableView<Movie> movieTable;

    @FXML
    private TableColumn<Movie, String> titleColumn;

    @FXML
    private TableColumn<Movie, String> yearColumn;

    @FXML
    private TableColumn<Movie, String> identifierColumn;

    @FXML
    private TableColumn<Movie, String> typeColumn;

    @FXML
    private TextField filterString;

    @FXML
    private Button submitButton;

    HttpHandler handler = new HttpHandler("feb7be56");

    ObservableList<Movie> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        identifierColumn.setCellValueFactory(new PropertyValueFactory<>("imdbID"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        submitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Set the observableArrayList with the filtered movies, the filter string is retrieved from the textfield
                list = FXCollections.observableArrayList(handler.filteredRequest(filterString.getText()));
                //update the table with the new list
                if(list.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText(new StringBuilder("No movies found with the filter: ").append(filterString.getText()).toString());
                    alert.showAndWait();
                }
                movieTable.setItems(list);
            }
        });
        movieTable.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                //Get the selected movie from the table
                Movie selectedMovie = movieTable.getSelectionModel().getSelectedItem();
                if(selectedMovie != null) {
                    //Show the movie's data in a dialog
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Full Movie Information");
                    alert.setHeaderText(null);
                    //Set the content of the dialog with the full movie data retreived through the handler fullMovieRequest method
                    alert.setContentText(handler.fullMovieRequest(selectedMovie.getImdbID()).toString());
                    alert.showAndWait();
                }
            }
        });
    }

}
