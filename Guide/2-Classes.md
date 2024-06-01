# Classes 🎥
To represent the movies/TV series/videogames received by the OMDb database, we decided to create two classes:

* `Movie`

```java
public class Movie {
    public String title = "N/A";
    public String year = "N/A";
    public String imdbID = "N/A";
    public String type = "N/A";
    public String poster = "N/A";
}
```

* `FullMovie` (which inherits from `Movie`)
  
```java
public final class Fullmovie extends Movie{
    /*
    *   title, year, poster, type and imdbID are 
    *   inherited from the Movie class
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
}
```

The division into two classes is due to the fact that the quantity of information retrieved depends on the search parameter. A request for a single item(by `title` or `IMDbID`) returns all its' details, unlike a request for multiple items(by `title`), which provides only essential information(`title`, `year`, `type`,`IMDbID` and `poster`).

Therefore, the `Movie` class contains what is returned by a multiple request, whereas `FullMovie` what is received from a single(detailed) request.

##

WORK IN PROGRESS...

##

### Jump to next section -> [3]()

##