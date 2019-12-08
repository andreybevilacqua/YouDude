package com.abevilacqua.youdude.controller.level_2;

import com.abevilacqua.youdude.controller.dto.PageImplDTO;
import com.abevilacqua.youdude.controller.dto.PlaylistDTO;
import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.controller.dto.PageImplDTO.mapper;
import static com.abevilacqua.youdude.controller.dto.PlaylistDTO.mapper;

@RestController
@RequestMapping("/level2/playlists")
public class PlaylistController {

  // todo: RESTFul, Cache, Spring Admin, Evo Suite

  private PlaylistService playlistService;

  @Autowired
  public PlaylistController(PlaylistService playlistService) {
    this.playlistService = playlistService;
  }

  @GetMapping
  public ResponseEntity<PageImplDTO<PlaylistDTO>> getAllPlaylists(
      @RequestParam(value = "page", defaultValue = "0") final int page,
      @RequestParam(value = "size", defaultValue = "10") final int size,
      @RequestParam(value = "sort", defaultValue = "id") final String sortBy) {
    CompletableFuture<Page<Playlist>> playlists = playlistService.getAllPlaylists(page, size, sortBy);
    Page<Playlist> playlistPage = playlists.join();
    return new ResponseEntity<>(mapper(playlistPage), HttpStatus.OK);
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<PageImplDTO<PlaylistDTO>> getPlaylistPerUser(
      @PathVariable("user_id") long user_id,
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "size", defaultValue = "10") int size,
      @RequestParam(value = "sort", defaultValue = "id") String sortBy) {
    CompletableFuture<Page<Playlist>> playlistsFromUser = playlistService.getAllFromUser(page, size, sortBy, user_id);
    Page<Playlist> playlistPage = playlistsFromUser.join();
    return new ResponseEntity<>(mapper(playlistPage), HttpStatus.OK);
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
