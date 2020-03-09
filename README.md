# YouDude
An API to play with Spring and Java.

## Requests example

### User
REST with Page endpoint: `http://localhost:8080/youdude/level2/users`

RESTFul endpoint: `http://localhost:8080/youdude/level3/users`

#### JSON

    {
        "id": 1,
        "name": "user-1",
        "creationDate": "2020-03-08"
    }

### Video
REST with Page endpoint: `http://localhost:8080/youdude/level2/videos`

RESTFul endpoint: `http://localhost:8080/youdude/level3/videos`

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
REST with Page endpoint: `http://localhost:8080/youdude/level2/playlists`

RESTFul endpoint: `http://localhost:8080/youdude/level3/playlists`

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
