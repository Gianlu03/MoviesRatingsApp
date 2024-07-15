package org.example.moviesratingsapp;

import org.example.moviesratingsapp.model.Fullmovie;
import org.example.moviesratingsapp.model.Movie;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HttpHandler {

    /**
        Comments need to be revised and rewritten according to Javadoc standard.
        This class provides a controller to handle the connection to the OMDb API.
        public String requestString = "http://www.omdbapi.com/?apikey=[" + myKey + "]&";
     */

    String APIkey = "";
    StringBuilder requestString = new StringBuilder("https://www.omdbapi.com/?");
    HttpClient client;
    HttpRequest request;
    HttpResponse<String> response;

    /**
     * @param APIkey
     * Constructor for the HttpHandler class.
     * Initialize the HttpClient object.
     * Takes an API key as a parameter, then creates the request string according to the argument.
     */

    public HttpHandler(String APIkey) {
        try {
            this.client = HttpClient.newHttpClient();
        } catch (UncheckedIOException UIOe) {
            System.out.println("Error: " + UIOe.getCause());
        }
        this.APIkey = APIkey;
        this.requestString.append("apikey=");
        this.requestString.append(this.APIkey);
        this.requestString.append("&");
    }

    /*
    * Resets the request string to the initial state: https://www.omdbapi.com/?apikey=APIKey
    * */
    public void resetRequestString(){
        this.requestString = new StringBuilder("https://www.omdbapi.com/?").append("apikey=").append(this.APIkey).append("&");
    }

    /**
     * This method creates the connection with the OMDb's API, by sending a Http request.
     * It could be overloaded accordingly to the parameters.
     * that should be used to filter the data.
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
            resetRequestString();     //Every time a request is sent either correctly or wrongly, it resets URI string for future requests
        }
        return (JSONObject) JSONValue.parse(response.body());
    }

    /**
     * TODO: it is necessary to prepare the HttpHandler class to handle filtered requests
     * @param filter is the string used to perform the searching
     * @return a list of JSONObject instances representing the movies targeted by the filter
     */
    public ArrayList<Movie> filteredRequest(String filter){
        ArrayList<Movie> result = new ArrayList<Movie>();
        //Add the filter to the request string
        this.requestString.append("s=").append(URLEncoder.encode(filter, StandardCharsets.UTF_8).replace("+", "%20"));
        //Send the request, the return is the JSON containing a list of JSON
        JSONObject returnedJson = submitRequest();

        //check if at least a film was found (response == True)
        if(returnedJson.get("Response").equals("True")){
            //Get the JSONArray of movies from the JSONObject
            JSONArray JS0Nlist = (JSONArray) returnedJson.get("Search");
            result = new ArrayList<Movie>();
            for (Object o : JS0Nlist) {
                //Get a single JSONObject from the JSONArray
                JSONObject movieJson = (JSONObject) o;
                //Add a new movie that sets its attributes from the JSONObject values
                result.add(new Movie(movieJson));
            }
        }
        return result;
    }

    public Movie fullMovieRequest(String imdbID){
        //Add the id to the request string to retreive the full movie data
        this.requestString.append("i=").append(imdbID);
        //Send the request with the URI built: receives a JSON with the full data
        JSONObject returnedJson = submitRequest();
        //Return new anonymous Fullmovie object -> will be displayed on alert pop up
        return new Fullmovie(returnedJson);
        //URI reset performed by submitRequest();
    }
    public StringBuilder getRequestString() {
        return requestString;
    }

    public HttpClient getClient() {
        return client;
    }

    public HttpRequest getRequest() {
        return request;
    }

    public HttpResponse<String> getResponse() {
        return response;
    }

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
