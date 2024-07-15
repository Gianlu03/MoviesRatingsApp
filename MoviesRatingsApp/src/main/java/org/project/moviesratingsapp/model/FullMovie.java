package org.project.moviesratingsapp.model;

import org.json.simple.JSONObject;

/**
 * Class that represents the entity returned by the API when a single(full) movie is requested
 * All the attributes are returned in Json format by the API.
 * */

public final class FullMovie extends Movie{

    /**
    * title, year, poster, type and imdbID are inherited from the Movie class
    * */
    private String runtime = "N/A";
    private String genre = "N/A";
    private String director = "N/A";
    private String language = "N/A";
    private String country = "N/A";
    private String metascore = "N/A";
    private String imdbRating = "N/A";
    private boolean response = false;

    /**
     * @param decodedJson response http body decoded as a JSONObject instance
     * Constructor that builds the movie object using the data retrieved with the HttpHandler.
     * If the movie does not exist, the objects maintains the default values.
     */
    public FullMovie(JSONObject decodedJson){
        super(decodedJson);
        if(decodedJson.get("Response").equals("false")){
            System.out.println("The movie does not exist");
        } else{
            setRuntime(decodedJson.get("Runtime").toString());
            setGenre(decodedJson.get("Genre").toString());
            setDirector(decodedJson.get("Director").toString());
            setLanguage(decodedJson.get("Language").toString());
            setCountry(decodedJson.get("Country").toString());
            setMetascore(decodedJson.get("Metascore").toString());
            setImdbRating(decodedJson.get("imdbRating").toString());
        }
        setResponse((String)decodedJson.get("Response"));
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public void setResponse(String response) { this.response = response.equals("True");}

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getMetascore() {
        return metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public boolean isResponse() {
        return response;
    }

}
