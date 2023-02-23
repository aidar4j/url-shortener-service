# url-shortener-service
URL Shortener Service is a MVP Product that serves to shorten given URLs. 

## Stack
 - Kotlin (JDK 19)
 - Spring Boot
 - Postgresql
 - Flyway

## Endpoints

The application has two endpoints:

### 1. Shorten URL
This endpoint accepts a POST request and generates a shortened URL key for the original URL provided in the request body. The URL key is returned in the response.

Request:
```
POST api/v1/shorten
Content-Type: application/json

{
  "url": "https://example.com/very/long/url/that/needs/to/be/shortened"
}
```
Response:
```
HTTP/1.1 200 OK
Content-Type: application/json

{
  "urlKey": "R8I8bCCH"
}
```

### 2. Get Original URL
This endpoint accepts a GET request and returns the original URL for a given URL key.

Request:
```
GET api/v1/original_url/{urlKey}
```
Response:
```
HTTP/1.1 200 OK
Content-Type: application/json

{
  "urlKey": "R8I8bCCH"
}
```
## Setup

### Prerequisites
+ JDK 19
+ Postgresql
+ Flyway

### Steps
1. Clone the repository:
```
git clone https://github.com/aidar4j/url-shortener-service.git
```

2. Create a new database in Postgresql.
3. Set the database URL, username, and password in the application.properties file.
4. Run the application:
```
./gradlew bootRun
```
5. (Optional) If you are using Flyway, run the database migrations:
```
./gradlew flywayMigrate
```

## Explanation of Project Structure
+ UrlShortenerApplication.kt is the main entry point of the application
+ controller/UrlShortenerController.kt contains the implementation of the endpoints
+ service/DefaultUrlShortenerService.kt contains business logic of url shortening
+ domain/entity/Url.kt is the domain model for a URL and repository/UrlRepository.kt is the repository for managing URLs in the database
+ test directory contains the unit tests for the controller, service classes and integration tests

## Further enhancement ideas

Here are some ideas for further enhancements to the URL Shortener Service:
Features:
+ Add more "urlKey" generation algorithms (UUID, Combination of different encodings)
+ Implement expiration date feature of shortened url to save up space on DB
+ Allow users to choose their own custom short URLs instead of generating random ones. This can make the service more user-friendly and allow for easier sharing of the shortened links.
+ Add a feature to generate QR codes for each shortened URL, making it easy to share the link through physical media such as business cards, posters.

Development enhancements:
+ Implement strategy pattern for different shortening algorithms
+ Build docker and k8s deployments and reject profile based deployment
+ Build CICD via Gitlab CI