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
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.controller.dto.PageImplDTO.pageImplDTOMapper;
import static com.abevilacqua.youdude.controller.dto.VideoDTO.mapper;

@RestController
@RequestMapping("/level2/videos")
public class VideoController {

  // todo: RESTFul, Cache, Spring Admin, Evo Suite

  private VideoService videoService;

  @Autowired
  public VideoController(VideoService videoService) {
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
    return new ResponseEntity<>(pageImplDTOMapper(videos), HttpStatus.OK);
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<Page<VideoDTO>> getVideosPerUser(
      @PathVariable("user_id") long user_id,
      @RequestParam(value = "page", defaultValue = "0") final int page,
      @RequestParam(value = "size", defaultValue = "10") final int size,
      @RequestParam(value = "sort", defaultValue = "id") final String sortBy) {
    CompletableFuture<Page<Video>> completableFuture = videoService.getAllFromUser(user_id, page, size, sortBy);
    Page<VideoDTO> videos = completableFuture
        .join()
        .map(VideoDTO::mapper);
    return new ResponseEntity<>(pageImplDTOMapper(videos), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<VideoDTO> createVideo(@RequestBody Video video) {
    CompletableFuture<Video> completableFuture = videoService.createVideo(video);
    return new ResponseEntity<>(mapper(completableFuture.join()), HttpStatus.CREATED);
  }

  @PutMapping
  public ResponseEntity<VideoDTO> updateVideo(@RequestBody Video video) {
    CompletableFuture<Optional<Video>> completableFuture = videoService.updateVideo(video);
    return completableFuture.join()
        .map(VideoDTO::mapper)
        .map(v -> new ResponseEntity<>(v, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{video_id}")
  public ResponseEntity<VideoDTO> deleteVideo(@PathVariable(value = "video_id") long video_id) {
    CompletableFuture<Optional<Video>> completableFuture = videoService.deleteVideo(video_id);
    return completableFuture
        .join()
        .map(VideoDTO::mapper)
        .map(videoDTO -> new ResponseEntity<>(videoDTO, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
