# YouDude
An API to play with Spring and Java.

To run it:
    
`mvn clean install -U`

`docker-compose -f compose.yml up -d`

## Technologies
    - Java 11
    - Spring Boot
    - Spring JPA
    - Spring HATEOAS
    - Spring Cache
    - Lombok
    - Flyway
    - PostgreSQL
    - H2 - tests
    - JUnit5
    - Mockito

## Requests example

### User
REST with Page endpoint: `http://localhost:8080/youdude/rest/users`

RESTFul endpoint: `http://localhost:8080/youdude/hateoas/users`

#### JSON

    {
        "id": 1,
        "name": "user-1",
        "creationDate": "2020-03-08"
    }

### Video
REST with Page endpoint: `http://localhost:8080/youdude/rest/videos`

RESTFul endpoint: `http://localhost:8080/youdude/hateoas/videos`

#### JSON

    {
        "video_id": 4,
        "name": "video-1",
        "subject": "subject 1",
        "duration": 10,
        "category": "COMEDY",
        "user_id": 1
    }

### Playlist
REST with Page endpoint: `http://localhost:8080/youdude/rest/playlists`

RESTFul endpoint: `http://localhost:8080/youdude/hateoas/playlists`

#### JSON

    {
       "id":32,
       "name":"playlist-32",
       "videos":[
         "videos":[
           {
             "id": 4,
             "name": "video-1",
             "subject": "subject 1",
             "duration": 10,
             "category": "COMEDY",
             "user_id": 1
           }
        ],
       "user":{
          "id":1,
          "name":"user-1",
          "creationDate":"2020-03-09"
       }
    }
