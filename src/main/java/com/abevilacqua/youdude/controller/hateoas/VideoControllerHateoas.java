package com.abevilacqua.youdude.controller.hateoas;

import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.model.resource.VideoResource;
import com.abevilacqua.youdude.model.resource.VideoResourceAssembler;
import com.abevilacqua.youdude.service.VideoService;
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
@RequestMapping(value = "/hateoas/videos", produces = "application/hal+json")
public class VideoControllerHateoas {

  private final VideoService videoService;

  @Autowired
  public VideoControllerHateoas(final VideoService videoService) {
    this.videoService = videoService;
  }

  @GetMapping
  public ResponseEntity<CollectionModel<VideoResource>> getAllVideos() {
    CompletableFuture<List<Video>> videosCompletableFuture = videoService.getAllVideos();

    CollectionModel<VideoResource> collectionModel =
        new VideoResourceAssembler().toCollectionModel(videosCompletableFuture.join());
    collectionModel
        .getContent()
        .forEach(videoResource ->
            videoResource
                .add(WebMvcLinkBuilder
                .linkTo(methodOn(UserControllerHateoas.class).getUserById(videoResource.getUser().getId()))
                .withRel("user-link")));
    collectionModel.add(WebMvcLinkBuilder
        .linkTo(methodOn(VideoControllerHateoas.class).getAllVideos())
        .withRel("self"));

    return new ResponseEntity<>(collectionModel, HttpStatus.OK);
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<CollectionModel<VideoResource>> getVideosPerUser(@PathVariable final UUID id) {
    CompletableFuture<List<Video>> completableFuture = videoService.getAllFromUser(id);

    CollectionModel<VideoResource> videoResourceCollectionModel =
        new VideoResourceAssembler().toCollectionModel(completableFuture.join());

    videoResourceCollectionModel.add(WebMvcLinkBuilder
        .linkTo(methodOn(VideoControllerHateoas.class).getVideosPerUser(id))
        .withRel("self"));

    return new ResponseEntity<>(videoResourceCollectionModel, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<VideoResource> getVideoById(@PathVariable final UUID id) {
    CompletableFuture<Optional<Video>> completableFutureOptional = videoService.getVideoById(id);

    return completableFutureOptional
        .join()
        .map(video -> {
          VideoResource videoResource = new VideoResourceAssembler().toModel(video);
          videoResource
              .add(WebMvcLinkBuilder
                  .linkTo(methodOn(VideoControllerHateoas.class).getAllVideos())
                  .withRel("base-link"));
          return new ResponseEntity<>(videoResource, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<VideoResource> createVideo(@RequestBody final Video video) {
    CompletableFuture<Video> completableFuture = videoService.createVideo(video);
    VideoResource videoResource = new VideoResourceAssembler().toModel(completableFuture.join());
    return new ResponseEntity<>(videoResource, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<VideoResource> deleteVideo(@PathVariable(value = "id") final UUID id) {
    CompletableFuture<Optional<Video>> completableFuture = videoService.deleteVideo(id);
    return completableFuture
        .join()
        .map(video -> new ResponseEntity<>(new VideoResourceAssembler().toModel(video), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
