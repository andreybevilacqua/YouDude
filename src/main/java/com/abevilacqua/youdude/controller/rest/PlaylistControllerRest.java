package com.abevilacqua.youdude.controller.rest;

import com.abevilacqua.youdude.controller.dto.PageImplDTO;
import com.abevilacqua.youdude.controller.dto.PlaylistDTO;
import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.service.PlaylistService;
import com.abevilacqua.youdude.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.controller.dto.PageImplDTO.pageMapper;
import static com.abevilacqua.youdude.controller.dto.PlaylistDTO.mapper;

@RestController
@RequestMapping("/rest/playlists")
public class PlaylistControllerRest {

  // todo: Evo Suite, gRPC, pitest

  private final PlaylistService playlistService;
  private final SecurityService securityService;

  @Autowired
  public PlaylistControllerRest(final PlaylistService playlistService,
                                final SecurityService securityService) {
    this.playlistService = playlistService;
    this.securityService = securityService;
  }

  @GetMapping
  public ResponseEntity<PageImplDTO<PlaylistDTO>> getAllPlaylists(
      @RequestParam(value = "page", defaultValue = "0") final int page,
      @RequestParam(value = "size", defaultValue = "10") final int size,
      @RequestParam(value = "sort", defaultValue = "id") final String sortBy,
      @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    System.out.println("Thread running getAllPlaylists controller: " + Thread.currentThread());
    CompletableFuture<Page<Playlist>> playlists = playlistService.getAllPlaylists(page, size, sortBy);
    Page<PlaylistDTO> playlistPage = playlists
        .join()
        .map(PlaylistDTO::mapper);
    return new ResponseEntity<PageImplDTO<PlaylistDTO>>(pageMapper(playlistPage), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlaylistDTO> getPlaylistById(@PathVariable("id") final UUID id,
                                                     @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    return playlistService
        .getById(id)
        .map(playlist -> new ResponseEntity<>(mapper(playlist), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<PlaylistDTO> createPlayList(@RequestBody final Playlist playlist,
                                                    @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    CompletableFuture<Playlist> playlistCompletableFuture = playlistService.createPlaylist(playlist);
    return new ResponseEntity<>(mapper(playlistCompletableFuture.join()), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<PlaylistDTO> deletePlaylist(@PathVariable(value = "id") final UUID id,
                                                    @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    CompletableFuture<Optional<Playlist>> optionalCompletableFuture = playlistService.deletePlaylist(id);
    return optionalCompletableFuture.join()
        .map(PlaylistDTO::mapper)
        .map(playlist -> new ResponseEntity<>(playlist, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
