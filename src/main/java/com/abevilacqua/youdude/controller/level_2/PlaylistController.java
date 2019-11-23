package com.abevilacqua.youdude.controller.level_2;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/level2/playlists")
public class PlaylistController {

  // todo: CompletableFuture, Paging, RESTFul, Cache, Spring Admin, improves JSON

  private PlaylistService playlistService;

  @Autowired
  public PlaylistController(PlaylistService playlistService) {
    this.playlistService = playlistService;
  }

  @GetMapping
  public ResponseEntity<List<Playlist>> getAllPlaylists() {
    return new ResponseEntity<>(playlistService.getAllPlaylists(), HttpStatus.OK);
  }

  @GetMapping("{user_id}")
  public ResponseEntity<List<Playlist>> getPlaylistPerUser(@PathVariable("user_id") long user_id) {
    return new ResponseEntity<>(playlistService.getAllFromUser(user_id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Playlist> createPlayList(@RequestBody Playlist playlist) {
    return new ResponseEntity<>(playlistService.createPlaylist(playlist), HttpStatus.CREATED);
  }

  @DeleteMapping("{playlist_id}")
  public ResponseEntity<Playlist> deletePlaylist(@PathVariable(value = "playlist_id") long playlist_id) {
    return playlistService.deletePlaylist(playlist_id)
        .map(playlist -> new ResponseEntity<>(playlist, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
