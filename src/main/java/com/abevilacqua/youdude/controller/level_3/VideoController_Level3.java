package com.abevilacqua.youdude.controller.level_3;

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
import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/level3/videos", produces = "application/hal+json")
public class VideoController_Level3 {

  private VideoService videoService;

  @Autowired
  public VideoController_Level3(final VideoService videoService) {
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
                .linkTo(methodOn(UserController_Level3.class).getUserById(videoResource.getUser().getId()))
                .withRel("user-link")));
    collectionModel.add(WebMvcLinkBuilder
        .linkTo(methodOn(VideoController_Level3.class).getAllVideos())
        .withRel("self"));

    return new ResponseEntity<>(collectionModel, HttpStatus.OK);
  }

  @GetMapping("/user/{user_id}")
  public ResponseEntity<CollectionModel<VideoResource>> getVideosPerUser(@PathVariable final long user_id) {
    CompletableFuture<List<Video>> completableFuture = videoService.getAllFromUser(user_id);

    CollectionModel<VideoResource> videoResourceCollectionModel =
        new VideoResourceAssembler().toCollectionModel(completableFuture.join());

    videoResourceCollectionModel.add(WebMvcLinkBuilder
        .linkTo(methodOn(VideoController_Level3.class).getVideosPerUser(user_id))
        .withRel("self"));

    return new ResponseEntity<>(videoResourceCollectionModel, HttpStatus.OK);
  }

  @GetMapping("/{video_id}")
  public ResponseEntity<VideoResource> getVideoById(@PathVariable final long video_id) {
    CompletableFuture<Optional<Video>> completableFutureOptional = videoService.getVideoById(video_id);

    return completableFutureOptional
        .join()
        .map(video -> {
          VideoResource videoResource = new VideoResourceAssembler().toModel(video);
          videoResource
              .add(WebMvcLinkBuilder
                  .linkTo(methodOn(VideoController_Level3.class).getAllVideos())
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

  @DeleteMapping("/{video_id}")
  public ResponseEntity<VideoResource> deleteVideo(@PathVariable(value = "video_id") final long video_id) {
    CompletableFuture<Optional<Video>> completableFuture = videoService.deleteVideo(video_id);
    return completableFuture
        .join()
        .map(video -> new ResponseEntity<>(new VideoResourceAssembler().toModel(video), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
