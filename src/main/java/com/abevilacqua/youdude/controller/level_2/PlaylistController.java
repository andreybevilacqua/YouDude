package com.abevilacqua.youdude.controller.level_2;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/level2_playlist")
public class PlaylistController {

  private PlaylistService playlistService;

  @Autowired
  public PlaylistController(PlaylistService playlistService) {
    this.playlistService = playlistService;
  }

  @GetMapping("/playlists")
  public ResponseEntity getPlaylistPerUser(@PathVariable("user_id") long user_id) {
    return new ResponseEntity<>(playlistService.getAllFromUser(user_id), HttpStatus.OK);
  }

  @PostMapping("/playlists")
  public ResponseEntity createPlayList(@RequestBody Playlist playlist) {
    return new ResponseEntity<>(playlistService.createPlaylist(playlist), HttpStatus.CREATED);
  }

  @DeleteMapping("/playlists/{playlist_id}")
  public ResponseEntity deletePlaylist(@PathVariable(value = "playlist_id") long playlist_id) {
    return playlistService.deletePlaylist(playlist_id)
        .map(playlist -> new ResponseEntity<>(playlist, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
