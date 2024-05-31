package org.example.moviesratingsapp.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
import java.util.ArrayList;
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
    private TextField filterStringInput;

    @FXML
    private Button submitButton;

    HttpHandler handler = new HttpHandler("feb7be56");

    ObservableList<Movie> list = FXCollections.observableArrayList();
    public void handleButtonPress(){

        /*
            // Shadow style does not work: pls Gian help (not important at the moment)

        DropShadow shadow = new DropShadow();

        //Adding the shadow when the mouse cursor is on
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        button.setEffect(shadow);
                    }
                });
        //Removing the shadow when the mouse cursor is off
        button.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        button.setEffect(null);
                    }
                });


         */
    }

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
                list = FXCollections.observableArrayList(handler.filteredRequest(filterStringInput.getText()));
                //update the table with the new list
                if(list.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText(new StringBuilder("No movies found with the filter: ").append(filterStringInput.getText()).toString());
                    alert.showAndWait();
                }
                movieTable.setItems(list);
            }
        });

    }

}
