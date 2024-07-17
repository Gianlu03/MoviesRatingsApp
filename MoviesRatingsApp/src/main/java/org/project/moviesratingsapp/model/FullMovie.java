package org.project.moviesratingsapp.model;

import org.json.simple.JSONObject;

/**
 * The FullMovie class extends the Movie class and adds the following attributes:
 * */

public final class FullMovie extends Movie {

    /** the movie's runtime. */
    private String runtime = "N/A";
    /** the movie's genre. */
    private String genre = "N/A";
    /** the movie's director. */
    private String director = "N/A";
    /** the movie's language. */
    private String language = "N/A";
    /** the movie's country. */
    private String country = "N/A";
    /** the movie's metascore. */
    private String metascore = "N/A";
    /** the movie's IMDb rating. */
    private String imdbRating = "N/A";
    /** the request's response. */
    private boolean response = false;

    /**
     * Constructor with JsonObject parameter
     * Sets the attributes to the entity values returned by API
     * @param decodedJson the JSONObject that contains the data to be set in the attributes
     * */

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

    /**
     * Sets the metascore attribute
     * @param metascore the metascore to be set
     */
    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    /**
     * Sets the response attribute
     * @param response the response to be set
     */
    public void setResponse(String response) { this.response = response.equals("True");}

    /**
     * Sets the runtime attribute
     * @param runtime the runtime to be set
     */
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    /**
     * Sets the genre attribute
     * @param genre the genre to be set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Sets the director attribute
     * @param director the director to be set
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * Sets the language attribute
     * @param language the language to be set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Sets the country attribute
     * @param country the country to be set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Sets the imdbRating attribute
     * @param imdbRating the imdbRating to be set
     */
    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    /**
     * Gets the metascore attribute
     * @return the runtime attribute
     */
    public String getRuntime() {
        return runtime;
    }

    /**
     * Gets the genre attribute
     * @return the genre attribute
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Gets the director attribute
     * @return the director attribute
     */
    public String getDirector() {
        return director;
    }

    /**
     * Gets the language attribute
     * @return the language attribute
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Gets the country attribute
     * @return the country attribute
     */
    public String getCountry() {
        return country;
    }

    /**
     * Gets the metascore attribute
     * @return the metascore attribute
     */
    public String getMetascore() {
        return metascore;
    }

    /**
     * Gets the imdbRating attribute
     * @return the imdbRating attribute
     */
    public String getImdbRating() {
        return imdbRating;
    }

    /**
     * Gets the response attribute
     * @return the response attribute
     */
    public boolean isResponse() {
        return response;
    }

}
