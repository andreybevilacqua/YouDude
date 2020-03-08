# YouDude
An API to play with Spring and Java.

## Requests example

### User JSON
    {
        "id": 1,
        "name": "user-1",
        "creationDate": "2020-03-08"
    }

### Video JSON
    {
        "video_id": 4,
        "name": "video-1",
        "subject": "subject 1",
        "duration": 10,
        "category": "COMEDY",
        "user_id": 1
    }

### Playlist JSON
    {
       "id":32,
       "name":"playlist-32",
       "videos":[
          {
             "video_id":4,
             "name":"video-1",
             "subject":"subject 1",
             "duration":10,
             "category":"COMEDY",
             "user_id":1
          }
       ],
       "user":{
          "id":1,
          "name":"user-1",
          "creationDate":"2020-03-08"
       }
    }
