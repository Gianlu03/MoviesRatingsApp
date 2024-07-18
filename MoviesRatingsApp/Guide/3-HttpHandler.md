# HTTP Handler 👨‍💻
The most complex part of our code is the definition of the HTTP Handler class.
Here we define how we interact with the database.

Let's see the definition of this class:

```java
    public class HttpHandler {
        String APIkey;
        StringBuilder requestString;
        HttpClient client;
        HttpRequest request;
        HttpResponse<String> response;
```

* `APIkey`: the API key used to access the database (set by the constructor) 
* `requestString`: a `String` containing the HTTP request URL that is built step by step
* `HttpClient`, `HttpRequest` `HttpResponse` are three elements of the HTTP package, all of them are used to prepare the connection, send a request and receive the response from the API

---

Let's now take a look to the constructor, it only takes the API key as parameter:

```java
public HttpHandler(String APIkey) {
        this.requestString = new StringBuilder("https://www.omdbapi.com/?");
        try {
            // We istantiate a new HTTP client
            this.client = HttpClient.newHttpClient();
        } catch (UncheckedIOException UIOe) {
            System.out.println("Error: " + UIOe.getCause());
        }
        this.APIkey = APIkey;
        this.requestString.append("apikey=").append(this.APIkey).append("&");
    }
}
```
This prepare the basis of the `requestString`, appending the API key to the OMDb URL.

---

Here follows the methods used to send a request (works for both string-filtered and id-filtered requests):

```java
public void resetRequestString(){
    this.requestString = new StringBuilder("https://www.omdbapi.com/?").append("apikey=").append(this.APIkey).append("&");
}
```

It resets the URL to its original form: link to the site followed by the API key. 
We use this method after every request is succesfully sent to reset the `requestString` attribute
to its original value.

---

```java
public JSONObject submitRequest()  {
    // We build the URL!
    this.request = HttpRequest.newBuilder()
            .uri(URI.create(String.valueOf(this.requestString)))
            .build();
    try{
        // We send the request to the API!
        this.response = this.client.send(this.request, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
        System.out.println("Error: " + e.getCause());
    }
    finally {
        resetRequestString();   // Resetting the request URL to its original pre-request form
    }
    // Returning the response as a JSONObject thanks to JSON Simple dependency
    return (JSONObject) JSONValue.parse(response.body());
}

```
At the end of each request method, we use `submitRequest()` to send the accomplished request to OMDb. We check for the possible exceptions while the `HttpClinet` object sends the request.
Every time the `try` block is executed, the `finally` block resets `requestString` thanks to `resetRequestString()`.

In the end, we return a JSONObject parsing the body of the response obtained. Now we are able to potentially fetch the retrieved data into the Graphical User Interface, as we will see better in 4-Controllers(link);

---

Now we can discuss the way we prepare the two type of requests:

### Simple string-filtered request for multiple movies

```java
public ArrayList<Movie> filteredRequest(String filter){
        ArrayList<Movie> result = new ArrayList<Movie>();
        // Simple workaround to replace the blank spaces and to encode them in UTF-8
        this.requestString.append("s=").append(URLEncoder.encode(filter, StandardCharsets.UTF_8).replace("+", "%20"));

        JSONObject returnedJson = submitRequest(); //SUBMIT THE REQUEST

        if(returnedJson.get("Response").equals("True")){
            // If the request went well, we retrieve the data
            JSONArray JS0Nlist = (JSONArray) returnedJson.get("Search");
            result = new ArrayList<Movie>();
            for (Object o : JS0Nlist) {
                JSONObject movieJson = (JSONObject) o;
                result.add(new Movie(movieJson));
            }
        }
        return result;
    }
```

`filteredRequest(String filter)` prepares a requestString with the `s=title` parameter, this kind of request receives more than one film that match with the parameter. 

It also replaces blank spaces with UTF-encoding (replacing them with `%20`), allowing more than a word to be entered into the research field.

The result of a string-filtered request for multiple items is stored in a `JSONArray` object: we can now iterate through it to get the single elements.

---

### imdbID-filtered request for a single detailed movie (FullMovie)

```java
public FullMovie fullMovieRequest(String imdbID){
        this.requestString.append("i=").append(imdbID); // Query parameter is the imdbID!
        JSONObject returnedJson = submitRequest();
        return new FullMovie(returnedJson);
    }
```
This is the last instance method of the class. Instead of `s=[title]` it appends to `requestString` the `i=[imdbID]` parameter. Searching by imdbID returns a single film with further details.

---

### Jump to next section → [4-Controllers](4-Controllers.md)
### [2-Models](2-Models.md) ← Jump to previous section
