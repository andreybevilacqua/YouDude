package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.repo.jpa.VideoRepo;
import com.abevilacqua.youdude.repo.pageable.VideoRepoPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.service.helper.ServiceHelper.simulateSlowService;
import static java.util.concurrent.CompletableFuture.completedFuture;

@Service
public class VideoService {

  private VideoRepoPageable videoRepoPageable;

  private VideoRepo videoRepo;

  private UserService userService;

  @Autowired
  public VideoService(final VideoRepoPageable videoRepoPageable,
                      final UserService userService,
                      final VideoRepo videoRepo) {
    this.videoRepoPageable = videoRepoPageable;
    this.userService = userService;
    this.videoRepo = videoRepo;
  }

  @Async
  @Cacheable("getAllVideosPageable")
  public CompletableFuture<Page<Video>> getAllVideos(final int page,
                                                     final int size,
                                                     final String sortBy) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return completedFuture(videoRepoPageable.findAll(pageable));
  }

  @Async
  @Cacheable("getAllVideos")
  public CompletableFuture<List<Video>> getAllVideos() {
    simulateSlowService();
    return completedFuture(videoRepo.findAll());
  }

  @Async
  @Cacheable("getAllFromUser")
  public CompletableFuture<Page<Video>> getAllFromUser(final long user_id,
                                                       final int page,
                                                       final int size,
                                                       final String sortBy) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    Optional<User> optionalUser = getOptionalUser(user_id);
    return optionalUser
        .map(user -> completedFuture(videoRepoPageable.findAllByUser(user, pageable)))
        .orElseGet(() -> completedFuture(Page.empty()));
  }

  @Async
  public CompletableFuture<Video> createVideo(final Video video) {
    return completedFuture(videoRepoPageable.save(video));
  }

  @Async
  public CompletableFuture<Optional<Video>> deleteVideo(final long video_id) {
    Optional<Video> video = videoRepoPageable.findById(video_id);
    if(video.isPresent()) {
      videoRepoPageable.delete(video.get());
      return completedFuture(video);
    }
    else return completedFuture(Optional.empty());
  }

  @Async
  public CompletableFuture<Optional<Video>> updateVideo(final Video video) {
    Optional<Video> optVideo = videoRepoPageable.findById(video.getId());
    if(optVideo.isPresent()) {
      Video tempVideo = optVideo.get();
      tempVideo.setName(video.getName());
      tempVideo.setDuration(video.getDuration());
      tempVideo.setUser(video.getUser());
      tempVideo.setSubject(video.getSubject());
      return completedFuture(Optional.of(videoRepoPageable.save(tempVideo)));
    }
    else return completedFuture(Optional.empty());
  }

  private Optional<User> getOptionalUser(final long user_id) {
    CompletableFuture<Optional<User>> threadUser = userService.getById(user_id);
    return threadUser.join();
  }
}
