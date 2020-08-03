package com.abevilacqua.youdude.controller.hateoas;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.resource.PlaylistResource;
import com.abevilacqua.youdude.model.resource.PlaylistResourceAssembler;
import com.abevilacqua.youdude.service.PlaylistService;
import com.abevilacqua.youdude.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/hateoas/playlists", produces = "application/hal+json")
public class PlaylistControllerHateoas {

  private final PlaylistService playlistService;
  private final SecurityService securityService;

  @Autowired
  public PlaylistControllerHateoas(final PlaylistService playlistService,
                                   final SecurityService securityService) {
    this.playlistService = playlistService;
    this.securityService = securityService;
  }

  @GetMapping
  public ResponseEntity<CollectionModel<PlaylistResource>> getAllPlaylists(@RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    CompletableFuture<List<Playlist>> playlists = playlistService.getAllPlaylists();

    CollectionModel<PlaylistResource> collectionModel =
        new PlaylistResourceAssembler().toCollectionModel(playlists.join());

    collectionModel
        .getContent()
        .forEach(playlistResource ->
            playlistResource
                .add(WebMvcLinkBuilder
                  .linkTo(methodOn(PlaylistControllerHateoas.class).getPlaylistById(playlistResource.getId(), token))
                  .withRel("playlist-link"))
                .add(WebMvcLinkBuilder
                  .linkTo(methodOn(UserControllerHateoas.class).getUserById(playlistResource.getUser().getId(), token))
                  .withRel("user-link")));

    return new ResponseEntity<>(collectionModel, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlaylistResource> getPlaylistById(@PathVariable("id") final UUID id,
                                                          @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    Optional<Playlist> optionalPlaylist = playlistService.getById(id);
    return optionalPlaylist
        .map(playlist -> {
          PlaylistResource playlistResource = new PlaylistResourceAssembler().toModel(playlist);
          return new ResponseEntity<>(playlistResource, HttpStatus.OK);
        })
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
