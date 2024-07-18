# HTTP Handler 👨‍💻
The richest part of our code is represented by the HTTP Handler class.
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

* `APIkey`: the APIkey used to access the database(set by the constructor). 
* `requestString`: a String containing the HTTP request we build step by step and getting ready to send
* `HttpClient`, `HttpRequest` `HttpResponse` are three elements of the http package, all of them are used to prepare the connection, send a request and receive the response from a URL.

---

Let us take a look to the constructor, it only takes the APIkey as parameter:

```java
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
}
```
This prepare the basis of the requestString, appending the apikey to the OMDb site

---

Here follow the methods used to send a request(works for both multiple and single(detailed) requests):

`resetRequestString`
```java
public void resetRequestString(){
    this.requestString = new StringBuilder("https://www.omdbapi.com/?").append("apikey=").append(this.APIkey).append("&");
}
```

resets the URL to its original form: link to the site followed by the APIkey. We use
this method after every request succesfully sent.

---

`submitRequest()`

```java
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

```
At the end of each request method, we use `submitRequest()` to send the accomplished request to OMDb. We check for the possible exceptions
while the client object sends the request.
Every time the `try` block executes, the `finally` block resets `requestString` thanks to `resetRequestString()`.

In the end, we return a JSONObject parsing the body of the response obtained. We will be able to fetch the data.

---

Now we can discuss the way we prepare the 2 kind of requests:

`filteredRequest(String filter)`
```java
public ArrayList<Movie> filteredRequest(String filter){
        ArrayList<Movie> result = new ArrayList<Movie>();  
        this.requestString.append("s=").append(URLEncoder.encode(filter, StandardCharsets.UTF_8).replace("+", "%20"));

        JSONObject returnedJson = submitRequest(); //SUBMIT THE REQUEST

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
```

`filteredRequest(String filter)` prepares a requestString with the `s=title` parameter, this kind of request receives more than one film that match with the parameter. 

It also replaces blank spaces with UTF-encoding(replacing them with `%20`), allowing more than a word to be entered into the research field.

A request for multiple items is  with a JSONArray object: We can now loop through it to get the single elements.

---

`fullMovieRequest`

```java
public FullMovie fullMovieRequest(String imdbID){
        this.requestString.append("i=").append(imdbID);
        JSONObject returnedJson = submitRequest();
        return new FullMovie(returnedJson);
    }

```
This is the last instance method of the class. Instead of `s=[title]` appends to `requestString` the `i=[imdbID]` parameter. Searching by imdbID returns a single film together with further details.

---

### Jump to next section → [4-Controllers](4-Controllers.md)
### [2-Models](2-Models.md) ← Jump to previous section
