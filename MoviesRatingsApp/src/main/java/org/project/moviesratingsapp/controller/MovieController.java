package org.project.moviesratingsapp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.project.moviesratingsapp.App;
import org.project.moviesratingsapp.HttpHandler;
import org.project.moviesratingsapp.model.FullMovie;
import org.project.moviesratingsapp.model.Movie;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Controller class for the Movie View.
 * The class has the following attributes:
 * movieTable: the TableView object that displays the movies
 * titleColumn: the TableColumn object that displays the movie's title
 * yearColumn: the TableColumn object that displays the movie's year
 * identifierColumn: the TableColumn object that displays the movie's identifier
 * typeColumn: the TableColumn object that displays the movie's type
 * filterString: the TextField object that contains the filter string
 * submitButton: the Button object that submits the filter string
 * handler: the HttpHandler object that handles the API requests
 * list: the ObservableList object that contains the movies
 * */

public class MovieController implements Initializable {

    /** The TableView object that displays the movies. */
    @FXML
    private TableView<Movie> movieTable;

    /** The TableColumn object that displays the movie's title. */
    @FXML
    private TableColumn<Movie, String> titleColumn;

    /** The TableColumn object that displays the movie's year. */
    @FXML
    private TableColumn<Movie, String> yearColumn;

    /** The TableColumn object that displays the movie's identifier. */
    @FXML
    private TableColumn<Movie, String> identifierColumn;

    /** The TableColumn object that displays the movie's type. */
    @FXML
    private TableColumn<Movie, String> typeColumn;

    /** The TextField object that contains the filter string. */
    @FXML
    private TextField filterString;

    /** The Button object that submits the filter string. */
    @FXML
    private Button submitButton;

    /** The HttpHandler object that handles the API requests. */
    private final HttpHandler handler = new HttpHandler("feb7be56");

    /** The ObservableList object that contains the movies. */
    ObservableList<Movie> list = FXCollections.observableArrayList();

    /**
     * Method that creates an Alert object with the specified title, header and content.<br>
     * The method sets the icon of the Alert window to the application's icon.
     * @param title the title of the Alert
     * @param header the header of the Alert
     * @param content the content of the Alert
     * @return the Alert object
     */
    private Alert getAlert(String title, String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        Image icon = new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/icon.png")));
        alertStage.getIcons().add(icon);
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        alert.setGraphic(imageView);
        return alert;
    }

    /**
     * Method that initializes the TableView with the data from the API.
     * The method initializes the columns with the respective attributes from the Movie class.<br>
     * The method sets the action for the submit button, which filters the movies according to the filter string.<br>
     * It also sets the action for the TableView, which opens a new window with the movie's details.
     * @param url the URL to be initialized
     * @param resourceBundle the ResourceBundle to be initialized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));
        identifierColumn.setCellValueFactory(new PropertyValueFactory<>("imdbID"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        submitButton.setOnMouseClicked(mouseEvent -> {
            String filter = filterString.getText().trim();
            if(filter.isEmpty())
                return;
            list = FXCollections.observableArrayList(handler.filteredRequest(filter));
            if(list.isEmpty()){
                Alert alert = getAlert("A message for you...",
                        "Ops! No movies found",
                        "Unable to find movies with the filter: " + "\"" + filterString.getText() + "\"");
                alert.showAndWait();
                movieTable.setDisable(true);
            } else
                movieTable.setDisable(false);
            movieTable.setItems(list);
        });
        movieTable.setOnMouseClicked(mouseEvent -> {
            Movie selectedMovie = movieTable.getSelectionModel().getSelectedItem();
            if(Objects.isNull(selectedMovie))
                return;
            FullMovie requestedMovie = handler.fullMovieRequest(selectedMovie.getImdbID());
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("movie-detail.fxml"));
                Parent root = fxmlLoader.load();
                Stage newStage = new Stage();
                newStage.setTitle("Details of: \"" + selectedMovie.getTitle() + "\"");
                newStage.setScene(new Scene(root));
                newStage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("images/icon.png"))));
                newStage.setResizable(false);
                newStage.show();
                MovieDetailController controller = fxmlLoader.getController();
                controller.fill(requestedMovie);
                movieTable.getSelectionModel().clearSelection();
            }catch(Exception e){
                Alert alert = getAlert("Error Dialog",
                        null,
                        "An error occurred while trying to display the movie details.");
                alert.showAndWait();
            }
        });
    }

}
