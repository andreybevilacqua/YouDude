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

import static com.abevilacqua.youdude.controller.dto.PageImplDTO.pageMapper;
import static com.abevilacqua.youdude.controller.dto.PlaylistDTO.mapper;

@RestController
@RequestMapping("/level2/playlists")
public class PlaylistController_Level2 {

  // todo: RESTFul, Evo Suite, bug with the page/size/sort

  private PlaylistService playlistService;

  @Autowired
  public PlaylistController_Level2(final PlaylistService playlistService) {
    this.playlistService = playlistService;
  }

  @GetMapping
  public ResponseEntity<PageImplDTO<PlaylistDTO>> getAllPlaylists(
      @RequestParam(value = "page", defaultValue = "0") final int page,
      @RequestParam(value = "size", defaultValue = "10") final int size,
      @RequestParam(value = "sort", defaultValue = "id") final String sortBy) {
    System.out.println("Thread running getAllPlaylists controller: " + Thread.currentThread());
    CompletableFuture<Page<Playlist>> playlists = playlistService.getAllPlaylists(page, size, sortBy);
    Page<PlaylistDTO> playlistPage = playlists
        .join()
        .map(PlaylistDTO::mapper);
    return new ResponseEntity<PageImplDTO<PlaylistDTO>>(pageMapper(playlistPage), HttpStatus.OK);
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<PageImplDTO<PlaylistDTO>> getPlaylistPerUser(
      @PathVariable("user_id") final long user_id,
      @RequestParam(value = "page", defaultValue = "0") final int page,
      @RequestParam(value = "size", defaultValue = "10") final int size,
      @RequestParam(value = "sort", defaultValue = "id") final String sortBy) {
    CompletableFuture<Page<Playlist>> playlistsFromUser = playlistService.getAllFromUser(page, size, sortBy, user_id);
    Page<PlaylistDTO> playlistPage = playlistsFromUser
        .join()
        .map(PlaylistDTO::mapper);
    return new ResponseEntity<PageImplDTO<PlaylistDTO>>(pageMapper(playlistPage), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<PlaylistDTO> createPlayList(@RequestBody final Playlist playlist) {
    CompletableFuture<Playlist> playlistCompletableFuture = playlistService.createPlaylist(playlist);
    return new ResponseEntity<>(mapper(playlistCompletableFuture.join()), HttpStatus.CREATED);
  }

  @DeleteMapping("/{playlist_id}")
  public ResponseEntity<PlaylistDTO> deletePlaylist(@PathVariable(value = "playlist_id") final long playlist_id) {
    CompletableFuture<Optional<Playlist>> optionalCompletableFuture = playlistService.deletePlaylist(playlist_id);
    return optionalCompletableFuture.join()
        .map(PlaylistDTO::mapper)
        .map(playlist -> new ResponseEntity<>(playlist, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
