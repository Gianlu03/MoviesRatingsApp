# HTTP Handler 👨‍💻
The richest part of our code is represented by the HTTP Handler class.
Here we define how we interact with the database.

Let's see the definition of this class:

```java
    public class HttpHandler {
        String APIkey = "";
        StringBuilder requestString = new StringBuilder("https://www.omdbapi.com/?");
        HttpClient client;
        HttpRequest request;
        HttpResponse<String> response;
```

* `APIkey`: the APIkey used to access the database(set by the constructor). 
* `requestString`: a String containing the HTTP request we are building step by step and getting ready to send
* `HttpClient`, `HttpRequest` `HttpResponse` are three elements of the http package, all of them are used to prepare the connection, send a request and receive the response from a URL.

##

Let us take a look to the constructor, it only takes the APIkey as parameter:

```java
public HttpHandler(String APIkey) {
    try {
        this.client = HttpClient.newHttpClient();      //prepare Http client: this will send the request later
    } catch (UncheckedIOException UIOe) {              //check for exceptions
        System.out.println("Error: " + UIOe.getCause());
    }
    this.APIkey = APIkey;  
    this.requestString.append("apikey=");        //we append to the request URL the APIkey field
    this.requestString.append(this.APIkey);
    this.requestString.append("&");              //the & allows to append further parameters to the request
}
```
##

Here are the methods used to send a request(works for both simple and detailed requests):

`resetRequestString`
```java
public void resetRequestString(){
    this.requestString = new StringBuilder("https://www.omdbapi.com/?").append("apikey=").append(this.APIkey).append("&");
}
```

resets the URL to the original form: link to the site followed by the APIkey. We use
this method after every request succesfully sent.

##

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
        resetRequestString();     //Every time a request is sent either correctly or wrongly, it resets URI string for future requests
    }
    return (JSONObject) JSONValue.parse(response.body());
}

```
Finally, we use `submitRequest()` to send the accomplished request to OMDB. We check for the possible exceptions
while the client object sends the request.
Every time the `try` block executes, the `finally` block resets `requestString` thanks to the previous method.

In the end, We return a JSONObject parsing the body of the response obtained. We will be able to fetch the data.

## 

Now we can discuss the way we prepare the 2 kind of requests:

`filteredRequest(String filter)`
```java
public ArrayList<Movie> filteredRequest(String filter){
        ArrayList<Movie> result = new ArrayList<Movie>();  
        //Add the filter to the request string
        this.requestString.append("s=").append(URLEncoder.encode(filter, StandardCharsets.UTF_8).replace("+", "%20"));

        JSONObject returnedJson = submitRequest(); //send the request -> receive a JSONObject

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
```

`filteredRequest(String filter)` prepares a requestString with the `s=title` parameter, this asks for multiple films that match with it. A multiple response sends an array of JSONObjects, we can store it into a JSONArray.
We can now loop through this JSONArray and add elements to our List using the JSONObjects as parameter of our constructors.