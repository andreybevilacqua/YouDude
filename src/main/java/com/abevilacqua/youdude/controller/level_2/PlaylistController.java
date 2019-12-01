package com.abevilacqua.youdude.controller.level_2;

import com.abevilacqua.youdude.controller.dto.PlaylistDTO;
import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.abevilacqua.youdude.controller.dto.PlaylistDTO.mapper;

@RestController
@RequestMapping("/level2/playlists")
public class PlaylistController {

  // todo: Paging, Sort, RESTFul, Cache, Spring Admin

  private PlaylistService playlistService;

  @Autowired
  public PlaylistController(PlaylistService playlistService) {
    this.playlistService = playlistService;
  }

  @GetMapping
  public ResponseEntity<List<PlaylistDTO>> getAllPlaylists() {
    CompletableFuture<List<Playlist>> playlists = playlistService.getAllPlaylists();
    return new ResponseEntity<>(playlists
        .join()
        .stream()
        .map(PlaylistDTO::mapper)
        .collect(Collectors.toList()), HttpStatus.OK);
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<List<PlaylistDTO>> getPlaylistPerUser(@PathVariable("user_id") long user_id) {
    CompletableFuture<List<Playlist>> playlistsFromUser = playlistService.getAllFromUser(user_id);
    return new ResponseEntity<>(playlistsFromUser
        .join()
        .stream()
        .map(PlaylistDTO::mapper)
        .collect(Collectors.toList()), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<PlaylistDTO> createPlayList(@RequestBody Playlist playlist) {
    CompletableFuture<Playlist> playlistCompletableFuture = playlistService.createPlaylist(playlist);
    return new ResponseEntity<>(mapper(playlistCompletableFuture.join()), HttpStatus.CREATED);
  }

  @DeleteMapping("/{playlist_id}")
  public ResponseEntity<PlaylistDTO> deletePlaylist(@PathVariable(value = "playlist_id") long playlist_id) {
    CompletableFuture<Optional<Playlist>> optionalCompletableFuture = playlistService.deletePlaylist(playlist_id);
    return optionalCompletableFuture.join()
        .map(PlaylistDTO::mapper)
        .map(playlist -> new ResponseEntity<>(playlist, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
