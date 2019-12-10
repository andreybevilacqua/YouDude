package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.repo.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.service.helper.ServiceHelper.simulateSlowService;
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
  public CompletableFuture<Page<Video>> getAllVideos(int page, int size, String sortBy) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return completedFuture(videoRepo.findAll(pageable));
  }

  @Async
  public CompletableFuture<Page<Video>> getAllFromUser(long user_id, int page, int size, String sortBy) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    Optional<User> optionalUser = getOptionalUser(user_id);
    return optionalUser
        .map(user -> completedFuture(videoRepo.findAllByUser(user, pageable)))
        .orElseGet(() -> completedFuture(Page.empty()));
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

  private Optional<User> getOptionalUser(long user_id) {
    CompletableFuture<Optional<User>> threadUser = userService.getById(user_id);
    return threadUser.join();
  }
}
