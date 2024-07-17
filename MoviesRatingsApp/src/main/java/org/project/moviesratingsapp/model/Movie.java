package org.project.moviesratingsapp.model;

import org.json.simple.JSONObject;

/**
 * The Movie class represents a movie entity.
 * The class has the following attributes:
 * */

public class Movie {

    /** the movie's title. */
    public String title = "N/A";
    /** the movie's year. */
    public String year = "N/A";
    /** the movie's identifier. */
    public String imdbID = "N/A";
    /** the movie's type. */
    public String type = "N/A";
    /** the movie's poster. */
    public String poster = "N/A";

    /**
     * Constructor with JsonObject parameter.
     * Sets the attributes to the entity values returned by API.
     * @param decodedJson the JSONObject that contains the data to be set in the attributes
     * */
    public Movie(JSONObject decodedJson){
        setTitle((String)decodedJson.get("Title"));
        setYear((String)decodedJson.get("Year"));
        setImdbID((String)decodedJson.get("imdbID"));
        setType((String)decodedJson.get("Type"));
        setPoster((String)decodedJson.get("Poster"));
    }

    /**
     * Gets the title attribute.
     * @return the movie's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the year attribute.
     * @return the movie's year
     */
    public String getYear() {
        return year;
    }

    /**
     * Gets the imdbID attribute.
     * @return the movie's identifier
     */
    public String getImdbID() {
        return imdbID;
    }

    /**
     * Gets the type attribute.
     * @return the movie's type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the poster attribute.
     * @return the movie's poster
     */
    public String getPoster() {
        return poster;
    }

    /**
     * Sets the title attribute.
     * @param title the title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the year attribute.
     * @param year the year to be set
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Sets the imdbID attribute.
     * @param imdbID the imdbID to be set
     */
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    /**
     * Sets the type attribute.
     * @param type the type to be set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the poster attribute.
     * @param poster the poster to be set
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     * Overrides the toString method to print the attributes of the class.
     * @return a string with the attributes of the class
     */
    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}
