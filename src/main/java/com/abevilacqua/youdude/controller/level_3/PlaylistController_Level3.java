package com.abevilacqua.youdude.controller.level_3;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.resource.PlaylistResource;
import com.abevilacqua.youdude.model.resource.PlaylistResourceAssembler;
import com.abevilacqua.youdude.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/level3/playlists", produces = "application/hal+json")
public class PlaylistController_Level3 {

  private PlaylistService playlistService;

  @Autowired
  public PlaylistController_Level3(final PlaylistService playlistService) {
    this.playlistService = playlistService;
  }

  @GetMapping
  public ResponseEntity<CollectionModel<PlaylistResource>> getAllPlaylists() {
    CompletableFuture<List<Playlist>> playlists = playlistService.getAllPlaylists();

    CollectionModel<PlaylistResource> collectionModel =
        new PlaylistResourceAssembler().toCollectionModel(playlists.join());

    collectionModel
        .getContent()
        .forEach(playlistResource ->
            playlistResource
                .add(WebMvcLinkBuilder
                  .linkTo(methodOn(PlaylistController_Level3.class).getPlaylistById(playlistResource.getId()))
                  .withRel("playlist-link"))
                .add(WebMvcLinkBuilder
                  .linkTo(methodOn(UserController_Level3.class).getUserById(playlistResource.getUser().getId()))
                  .withRel("user-link")));

    return new ResponseEntity<>(collectionModel, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlaylistResource> getPlaylistById(@PathVariable("id") final long id) {
    CompletableFuture<Optional<Playlist>> optionalPlaylist = playlistService.getById(id);
    return optionalPlaylist
        .join()
        .map(playlist -> {
          PlaylistResource playlistResource = new PlaylistResourceAssembler().toModel(playlist);
          return new ResponseEntity<>(playlistResource, HttpStatus.OK);
        })
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/user/{user_id}")
  public ResponseEntity<CollectionModel<PlaylistResource>> getPlaylistPerUser(@PathVariable("user_id") final long user_id) {
    CompletableFuture<List<Playlist>> playlistsFromUser = playlistService.getAllFromUser(user_id);

    CollectionModel<PlaylistResource> collectionModel =
        new PlaylistResourceAssembler().toCollectionModel(playlistsFromUser.join());

    return new ResponseEntity<>(collectionModel, HttpStatus.OK);
  }

}
