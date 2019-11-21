package com.abevilacqua.youdude.controller.level_2;

import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/level2_video")
public class VideoController {

  private VideoService videoService;

  @Autowired
  public VideoController(VideoService videoService) {
    this.videoService = videoService;
  }

  @GetMapping("/videos/{user_id}")
  public ResponseEntity getVideosPerUser(@PathVariable("user_id") long user_id) {
    return new ResponseEntity<>(videoService.getAllFromUser(user_id), HttpStatus.OK);
  }

  @PostMapping("/videos")
  public ResponseEntity createVideo(@RequestBody Video video) {
    return new ResponseEntity<>(videoService.createVideo(video), HttpStatus.CREATED);
  }

  @PutMapping("/videos")
  public ResponseEntity updateVideo(@RequestBody Video video) {
    return videoService.updateVideo(video)
        .map(v -> new ResponseEntity<>(v, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/videos/{video_id}")
  public ResponseEntity deleteVideo(@PathVariable(value = "video_id") long video_id) {
    return videoService.deleteVideo(video_id)
        .map(video -> new ResponseEntity<>(video, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
