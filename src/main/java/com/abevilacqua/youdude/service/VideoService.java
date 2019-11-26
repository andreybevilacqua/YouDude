package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.repo.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.util.Collections.EMPTY_LIST;
import static java.util.concurrent.CompletableFuture.completedFuture;

@Service
public class VideoService {

  private VideoRepo videoRepo;

  private UserService userService;

  @Autowired
  public VideoService(VideoRepo videoRepo,
                      UserService userService) {
    this.videoRepo = videoRepo;
    this.userService = userService;
  }

  @Async
  public CompletableFuture<List<Video>> getAllVideos() {
    return completedFuture(videoRepo.findAll());
  }

  @Async
  public CompletableFuture getAllFromUser(long user_id) {
    CompletableFuture<Optional<User>> threadUser = userService.getById(user_id);
    Optional<User> optionalUser = threadUser.join();
    return optionalUser
        .map(user -> completedFuture(videoRepo.findAllByUser(user)))
        .orElseGet(() -> completedFuture(EMPTY_LIST));
  }

  @Async
  public CompletableFuture<Video> createVideo(Video video) {
    return completedFuture(videoRepo.save(video));
  }

  @Async
  public CompletableFuture<Optional<Video>> deleteVideo(long video_id) {
    Optional<Video> video = videoRepo.findById(video_id);
    if(video.isPresent()) {
      videoRepo.delete(video.get());
      return completedFuture(video);
    }
    else return completedFuture(Optional.empty());
  }

  @Async
  public CompletableFuture<Optional<Video>> updateVideo(Video video) {
    Optional<Video> optVideo = videoRepo.findById(video.getId());
    if(optVideo.isPresent()) {
      Video tempVideo = optVideo.get();
      tempVideo.setName(video.getName());
      tempVideo.setDuration(video.getDuration());
      tempVideo.setUser(video.getUser());
      tempVideo.setSubject(video.getSubject());
      return completedFuture(Optional.of(videoRepo.save(tempVideo)));
    }
    else return completedFuture(Optional.empty());
  }
}
