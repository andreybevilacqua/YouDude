package com.abevilacqua.youdude.controller.level_1_2;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.service.PlaylistService;
import com.abevilacqua.youdude.service.UserService;
import com.abevilacqua.youdude.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/youdude_level1_2")
public class YouDudeControllerLevel1_2 {

  private UserService userService;

  private VideoService videoService;

  private PlaylistService playlistService;

  @Autowired
  public YouDudeControllerLevel1_2(VideoService videoService,
                                   PlaylistService playlistService,
                                   UserService userService) {
    this.videoService = videoService;
    this.playlistService = playlistService;
    this.userService = userService;
  }

  @GetMapping("/users")
  public ResponseEntity getAllUsers() {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

  @GetMapping("/videos/{id}")
  public ResponseEntity getVideosPerUser(@PathVariable("user_id") long user_id) {
    return new ResponseEntity<>(videoService.getAllFromUser(user_id), HttpStatus.OK);
  }

  @GetMapping("/playlists")
  public ResponseEntity getPlaylistPerUser(@PathVariable("user_id") long user_id) {
    return new ResponseEntity<>(playlistService.getAllFromUser(user_id), HttpStatus.OK);
  }

  @PostMapping("/playlists")
  public ResponseEntity createPlayList(@RequestBody Playlist playlist) {
    return new ResponseEntity<>(playlistService.createPlaylist(playlist), HttpStatus.CREATED);
  }

  @PostMapping("/videos")
  public ResponseEntity createVideo(@RequestBody Video video) {
    return new ResponseEntity<>(videoService.createVideo(video), HttpStatus.CREATED);
  }

  @PutMapping("/videos")
  public ResponseEntity updateVideo(@RequestBody Video video) {
    return videoService.updateVideo(video)
        .map(v -> new ResponseEntity<>(v, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/videos/{video_id}")
  public ResponseEntity deleteVideo(@PathVariable(value = "video_id") long video_id) {
    return videoService.deleteVideo(video_id)
        .map(video -> new ResponseEntity<>(video, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/playlists/{playlist_id}")
  public ResponseEntity deletePlaylist(@PathVariable(value = "playlist_id") long playlist_id) {
    return playlistService.deletePlaylist(playlist_id)
        .map(playlist -> new ResponseEntity<>(playlist, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
