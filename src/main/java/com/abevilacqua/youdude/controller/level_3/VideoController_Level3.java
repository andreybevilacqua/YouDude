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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    collectionModel.add(WebMvcLinkBuilder
        .linkTo(methodOn(VideoController_Level3.class).getAllVideos())
        .withRel("self"));

    return new ResponseEntity<>(collectionModel, HttpStatus.OK);
  }

  @GetMapping("/user/{user_id}")
  public ResponseEntity<CollectionModel<VideoResource>> getVideoPerUser(@PathVariable final long user_id) {
    CompletableFuture<List<Video>> completableFuture = videoService.getAllFromUser(user_id);

    CollectionModel<VideoResource> videoResourceCollectionModel =
        new VideoResourceAssembler().toCollectionModel(completableFuture.join());

    videoResourceCollectionModel.add(WebMvcLinkBuilder
        .linkTo(methodOn(VideoController_Level3.class).getVideoPerUser(user_id))
        .withRel("self"));

    return new ResponseEntity<>(videoResourceCollectionModel, HttpStatus.OK);
  }
}
