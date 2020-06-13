package com.abevilacqua.youdude.controller.level_2;

import com.abevilacqua.youdude.controller.dto.PageImplDTO;
import com.abevilacqua.youdude.controller.dto.VideoDTO;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.controller.dto.PageImplDTO.pageMapper;
import static com.abevilacqua.youdude.controller.dto.VideoDTO.mapper;

@RestController
@RequestMapping("/level2/videos")
public class VideoController_Level2 {

  // todo: Evo Suite, gRPC

  private final VideoService videoService;

  @Autowired
  public VideoController_Level2(final VideoService videoService) {
    this.videoService = videoService;
  }

  @GetMapping
  public ResponseEntity<PageImplDTO<VideoDTO>> getAllVideos(
      @RequestParam(value = "page", defaultValue = "0") final int page,
      @RequestParam(value = "size", defaultValue = "10") final int size,
      @RequestParam(value = "sort", defaultValue = "id") final String sortBy) {
    CompletableFuture<Page<Video>> completableFuture = videoService.getAllVideos(page, size, sortBy);
    Page<VideoDTO> videos = completableFuture
        .join()
        .map(VideoDTO::mapper);
    return new ResponseEntity<PageImplDTO<VideoDTO>>(pageMapper(videos), HttpStatus.OK);
  }

  @GetMapping("/user/{user_id}")
  public ResponseEntity<PageImplDTO<VideoDTO>> getVideosPerUser(
      @PathVariable("user_id") final UUID user_id,
      @RequestParam(value = "page", defaultValue = "0") final int page,
      @RequestParam(value = "size", defaultValue = "10") final int size,
      @RequestParam(value = "sort", defaultValue = "id") final String sortBy) {
    CompletableFuture<Page<Video>> completableFuture = videoService.getAllFromUser(user_id, page, size, sortBy);
    Page<VideoDTO> videos = completableFuture
        .join()
        .map(VideoDTO::mapper);
    return new ResponseEntity<PageImplDTO<VideoDTO>>(pageMapper(videos), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<VideoDTO> getVideoById(@PathVariable("id") final UUID id) {
    CompletableFuture<Optional<Video>> completableFuture = videoService.getVideoById(id);
    return completableFuture
        .join()
        .map(video -> new ResponseEntity<>(VideoDTO.mapper(video), HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<VideoDTO> createVideo(@RequestBody final Video video) {
    CompletableFuture<Video> completableFuture = videoService.createVideo(video);
    return new ResponseEntity<>(mapper(completableFuture.join()), HttpStatus.CREATED);
  }

  @DeleteMapping("/{video_id}")
  public ResponseEntity<VideoDTO> deleteVideo(@PathVariable(value = "video_id") final UUID video_id) {
    CompletableFuture<Optional<Video>> completableFuture = videoService.deleteVideo(video_id);
    return completableFuture
        .join()
        .map(VideoDTO::mapper)
        .map(videoDTO -> new ResponseEntity<>(videoDTO, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
