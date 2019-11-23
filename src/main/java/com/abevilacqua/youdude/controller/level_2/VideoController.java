package com.abevilacqua.youdude.controller.level_2;

import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/level2/videos")
public class VideoController {

  // todo: CompletableFuture, Paging, RESTFul, Cache, Spring Admin, improves JSON result

  private VideoService videoService;

  @Autowired
  public VideoController(VideoService videoService) {
    this.videoService = videoService;
  }

  @GetMapping
  public ResponseEntity<List<Video>> getAllVideos() {
    return new ResponseEntity<>(videoService.getAllVideos(), HttpStatus.OK);
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<List<Video>> getVideosPerUser(@PathVariable("user_id") long user_id) {
    return new ResponseEntity<>(videoService.getAllFromUser(user_id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Video> createVideo(@RequestBody Video video) {
    return new ResponseEntity<>(videoService.createVideo(video), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<Video> updateVideo(@RequestBody Video video) {
    return videoService.updateVideo(video)
        .map(v -> new ResponseEntity<>(v, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("{video_id}")
  public ResponseEntity<Video> deleteVideo(@PathVariable(value = "video_id") long video_id) {
    return videoService.deleteVideo(video_id)
        .map(video -> new ResponseEntity<>(video, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
