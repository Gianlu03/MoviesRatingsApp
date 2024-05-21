package org.example.moviesratingsapp.model;

import org.json.simple.JSONObject;

/**
 * Class that represents the entity returned by the API when a single(full) movie is requested
 * All the attributes are returned in Json format by the API.
 * */

public final class Fullmovie extends Movie{

    /*
    * title, year, poster, type and imdbID are inherited from the Movie class
    * */
    private String rated = "N/A";
    private String released = "N/A";
    private String runtime  = "N/A";
    private String genre = "N/A";
    private String director = "N/A";
    private String writer = "N/A";
    private String actors = "N/A";
    private String plot = "N/A";
    private String language = "N/A";
    private String country = "N/A";
    private String awards = "N/A";
    private String metascore = "N/A";
    private String imdbRating = "N/A";
    private String imdbVotes = "N/A";
    private boolean response = false;

    /**
     * @param decodedJson response http body decoded as a JSONObject instance
     * Constructor that builds the movie object using the data retrieved with the HttpHandler.
     * If the movie does not exist, the objects maintains the default values.
     *
     */
    public Fullmovie(JSONObject decodedJson){

        /* Checking if the response went well
        *
        * TODO: in case the request goes wrong, the error needs to be handled properly
        *
        * */
        if(decodedJson.get("Response").equals("False")){
            System.out.println("The movie does not exist");
            return;
        }

        //The movie exists, so:
        //All the movie attributes are set from the decoded Json
        setImdbID((String)decodedJson.get("imdbID"));
        setTitle((String)decodedJson.get("Title"));
        setYear((String)decodedJson.get("Year"));
        setRated((String)decodedJson.get("Rated"));
        setReleased((String)decodedJson.get("Released"));
        setRuntime((String)decodedJson.get("Runtime"));
        setGenre((String)decodedJson.get("Genre"));
        setDirector((String)decodedJson.get("Director"));
        setWriter((String)decodedJson.get("Writer"));
        setActors((String)decodedJson.get("Actors"));
        setPlot((String)decodedJson.get("Plot"));
        setLanguage((String)decodedJson.get("Language"));
        setCountry((String)decodedJson.get("Country"));
        setAwards((String)decodedJson.get("Awards"));
        setPoster((String)decodedJson.get("Poster"));
        setMetascore((String)decodedJson.get("Metascore"));
        setImdbRating((String)decodedJson.get("imdbRating"));
        setImdbVotes((String)decodedJson.get("imdbVotes"));
        setType((String)decodedJson.get("Type"));
        setResponse((String)decodedJson.get("Response"));
    }

    /**
       TEMPORARY CONSTRUCTOR ONLY USED TO MAKE TESTS
     */


    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public void setResponse(String response) {
        this.response = response.equals("True");
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
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

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getAwards() {
        return awards;
    }

    public String getMetascore() {
        return metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public boolean isResponse() {
        return response;
    }

    @Override
    public String toString() {
        return "Movie [imdbID=" + imdbID + ", title=" + title + ", year=" + year + ", rated=" + rated + ", released="
                + released + ", runtime=" + runtime + ", genre=" + genre + ", director=" + director + ", writer="
                + writer + ", actors=" + actors + ", plot=" + plot + ", language=" + language + ", country=" + country
                + ", awards=" + awards + ", poster=" + poster + ", metascore=" + metascore + ", imdbRating="
                + imdbRating + ", imdbVotes=" + imdbVotes + ", type=" + type + ", response=" + response + "]";
    }
}
