package org.project.moviesratingsapp;

import org.project.moviesratingsapp.model.FullMovie;
import org.project.moviesratingsapp.model.Movie;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Class that handles the Http requests to the OMDb's API.
 * */

public class HttpHandler {

    /** the key used to authenticate the user. */
    String APIkey;
    /** the string used to build the URI. */
    StringBuilder requestString;
    /** the HttpClient object used to send the request. */
    HttpClient client;
    /** the HttpRequest object used to build the request. */
    HttpRequest request;
    /** the HttpResponse object used to store the response. */
    HttpResponse<String> response;

    /**
     * Constructor for the HttpHandler class.
     * Initialize the HttpClient object.
     * Takes an API key as a parameter, then creates the request string according to the argument.
     * @param APIkey the key used to authenticate the user
     */
    public HttpHandler(String APIkey) {
        this.requestString = new StringBuilder("https://www.omdbapi.com/?");
        try {
            this.client = HttpClient.newHttpClient();
        } catch (UncheckedIOException UIOe) {
            System.out.println("Error: " + UIOe.getCause());
        }
        this.APIkey = APIkey;
        this.requestString.append("apikey=").append(this.APIkey).append("&");
    }

    /**
     * Resets the request string to the initial state: <a href="https://www.omdbapi.com/?apikey=APIKey">...</a>
     * */
    public void resetRequestString(){
        this.requestString = new StringBuilder("https://www.omdbapi.com/?").append("apikey=").append(this.APIkey).append("&");
    }

    /**
     * This method creates the connection with the OMDb's API, by sending a Http request.
     * The response is stored in the response attribute.
     * The request is built using the requestString attribute. <br>
     * The response is parsed to a JSONObject instance and returned.
     * If an exception occurs, the error is printed and the method returns null.
     * @return the response result as a JSONObject instance
     */
    public JSONObject submitRequest()  {
        this.request = HttpRequest.newBuilder()
                .uri(URI.create(String.valueOf(this.requestString)))
                .build();
        try{
            this.response = this.client.send(this.request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("Error: " + e.getCause());
        }
        finally {
            resetRequestString();
        }
        return (JSONObject) JSONValue.parse(response.body());
    }

    /**
     * This method is used to retrieve a list of movies based on a filter.
     * The method builds the URI with the filter string.<br>
     * The response is parsed to a JSONObject instance and returned.
     * If an exception occurs, the error is printed and the method returns an empty list.
     * @param filter is the string used to perform the searching
     * @return a list of Movie instances representing the movies targeted by the filter(obtained from the API)
     */
    public ArrayList<Movie> filteredRequest(String filter){
        ArrayList<Movie> result = new ArrayList<Movie>();
        this.requestString.append("s=").append(URLEncoder.encode(filter, StandardCharsets.UTF_8).replace("+", "%20"));
        JSONObject returnedJson = submitRequest();
        if(returnedJson.get("Response").equals("True")){
            JSONArray JS0Nlist = (JSONArray) returnedJson.get("Search");
            result = new ArrayList<Movie>();
            for (Object o : JS0Nlist) {
                JSONObject movieJson = (JSONObject) o;
                result.add(new Movie(movieJson));
            }
        }
        return result;
    }

    /**
     * This method is used to retrieve the full data of a single movie.
     * The method builds the URI with the imdbID of the movie.<br>
     * The response is parsed to a JSONObject instance and returned.
     * If an exception occurs, the error is printed and the method returns null.
     * @param imdbID is the unique identifier of the movie
     * @return a FullMovie object with all the data of the movie
     */
    public FullMovie fullMovieRequest(String imdbID){
        this.requestString.append("i=").append(imdbID);
        JSONObject returnedJson = submitRequest();
        return new FullMovie(returnedJson);
    }

    /**
     * Gets the requestString attribute.
     * @return the request string
     */
    public StringBuilder getRequestString() {
        return requestString;
    }

    /**
     * Gets the HttpClient object.
     * @return the HttpClient object
     */
    public HttpClient getClient() {
        return client;
    }

    /**
     * Gets the HttpRequest object.
     * @return the HttpRequest object
     */
    public HttpRequest getRequest() {
        return request;
    }

    /**
     * Gets the HttpResponse object.
     * @return the HttpResponse object
     */
    public HttpResponse<String> getResponse() {
        return response;
    }

    /**
     * Overrides the toString method to print the attributes of the class.
     * @return a string with the attributes of the class
     */
    @Override
    public String toString() {
        return "HttpHandler{" +
                "requestString=" + requestString +
                ", client=" + client +
                ", request=" + request +
                ", response=" + response +
                '}';
    }
}
