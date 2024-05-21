package org.example.moviesratingsapp.model;

import org.json.simple.JSONObject;

/*
*   Class that represents the entity returned by API on a multiple request.
*   If the request parameter is s=, each json of the list contains: Title, Year, imdbID, Type and Poster
* */

public class Movie {
    public String title = "N/A";
    public String year = "N/A";
    public String imdbID = "N/A";
    public String type = "N/A";
    public String poster = "N/A";

    /*
    *    Constructor with JsonObject parameter
    *    Sets the attributes to the entity values returned by API
    * */
    public Movie(JSONObject decodedJson){
        setTitle((String)decodedJson.get("Title"));
        setYear((String)decodedJson.get("Year"));
        setImdbID((String)decodedJson.get("imdbID"));
        setType((String)decodedJson.get("Type"));
        setPoster((String)decodedJson.get("Poster"));
    }

    /*
    * Empty constructor to freely create objects of the class
    * */
    public Movie(){

    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return type;
    }

    public String getPoster() {
        return poster;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

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
