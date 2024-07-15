package org.project.moviesratingsapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.project.moviesratingsapp.App;
import org.project.moviesratingsapp.HttpHandler;
import org.project.moviesratingsapp.model.FullMovie;
import org.project.moviesratingsapp.model.Movie;

import java.net.URL;
import java.util.Objects;
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

    private final HttpHandler handler = new HttpHandler("feb7be56");

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
                String filter = filterString.getText().trim();
                //Set the observableArrayList with the filtered movies, the filter string is retrieved from the textfield
                if(filter.isEmpty())
                    return;
                list = FXCollections.observableArrayList(handler.filteredRequest(filter));
                //update the table with the new list
                if(list.isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("No movies found with the filter: " + filterString.getText());
                    alert.showAndWait();
                    movieTable.setDisable(true);
                } else
                    movieTable.setDisable(false);
                movieTable.setItems(list);
            }
        });
        movieTable.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                Movie selectedMovie = movieTable.getSelectionModel().getSelectedItem();
                if(selectedMovie == null)
                    return;
                FullMovie requestedMovie = handler.fullMovieRequest(selectedMovie.getImdbID());
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("movie-detail.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    Stage newStage = new Stage();
                    newStage.setTitle("Details of: \"" + selectedMovie.getTitle() + "\"");
                    newStage.setScene(new Scene(root));
                    newStage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/icon.png"))));
                    newStage.setResizable(false);
                    newStage.show();
                    MovieDetailController controller = fxmlLoader.getController();
                    controller.fill(requestedMovie);
                }catch(Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("An error occurred while trying to display the movie details.");
                    alert.showAndWait();
                }

            }
        });
    }

}
