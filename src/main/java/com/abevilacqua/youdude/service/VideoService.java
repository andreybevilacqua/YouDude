package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.repo.jpa.VideoRepo;
import com.abevilacqua.youdude.repo.pageable.VideoRepoPageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.service.GenericService.getAll;
import static com.abevilacqua.youdude.service.GenericService.simulateSlowService;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@Slf4j
@Service
public class VideoService {

  private final VideoRepoPageable videoRepoPageable;

  private final VideoRepo videoRepo;

  private final UserService userService;

  @Autowired
  public VideoService(VideoRepoPageable videoRepoPageable,
                      UserService userService,
                      VideoRepo videoRepo) {
    this.videoRepoPageable = videoRepoPageable;
    this.userService = userService;
    this.videoRepo = videoRepo;
  }

  public CompletableFuture<Page<Video>> getAllVideos(final int page,
                                                     final int size,
                                                     final String sortBy) {
    return getAll(videoRepoPageable, page, size, sortBy);
  }

  @Cacheable("getAllVideos")
  public CompletableFuture<List<Video>> getAllVideos() {
    simulateSlowService();
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return videoRepo.findAll();
    });
  }

  @Cacheable("getAllFromUserPageable")
  public CompletableFuture<Page<Video>> getAllFromUser(final UUID user_id,
                                                       final int page,
                                                       final int size,
                                                       final String sortBy) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    Optional<User> optionalUser = getOptionalUser(user_id);
    return optionalUser
        .map(user -> supplyAsync(() -> {
          System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
          return videoRepoPageable.findAllByUser(user, pageable);
        }))
        .orElseGet(() -> completedFuture(Page.empty()));
  }

  @Cacheable("getAllFromUser")
  public CompletableFuture<List<Video>> getAllFromUser(final UUID user_id) {
    simulateSlowService();
    Optional<User> optionalUser = getOptionalUser(user_id);
    return optionalUser
        .map(user -> supplyAsync(() -> {
          System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
          return videoRepo.findAllByUser(user);
        }))
        .orElseGet(() -> completedFuture(new ArrayList<>()));
  }

  @Cacheable("getVideoById")
  public CompletableFuture<Optional<Video>> getVideoById(final UUID id) {
    simulateSlowService();
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return videoRepo.findById(id);
    });
  }

  public CompletableFuture<Video> createVideo(final Video video) {
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return videoRepo.save(video);
    });
  }

  public CompletableFuture<Optional<Video>> deleteVideo(final UUID video_id) {
    Optional<Video> video = videoRepoPageable.findById(video_id);
    if(video.isPresent()) {
      return supplyAsync(() -> {
        System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
        videoRepo.delete(video.get());
        return video;
      });
    }
    else return completedFuture(Optional.empty());
  }

  private Optional<User> getOptionalUser(final UUID user_id) {
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return userService.getById(user_id);
    }).join().join();
  }
}
