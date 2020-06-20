package com.abevilacqua.youdude.service;

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

  @Autowired
  public VideoService(VideoRepoPageable videoRepoPageable,
                      VideoRepo videoRepo) {
    this.videoRepoPageable = videoRepoPageable;
    this.videoRepo = videoRepo;
  }

  @Cacheable("getAllVideosPageable")
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
  public CompletableFuture<Page<Video>> getAllFromUser(final UUID userId,
                                                       final int page,
                                                       final int size,
                                                       final String sortBy) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return videoRepoPageable.findAllByUser(userId, pageable);
    });
  }

  @Cacheable("getAllFromUser")
  public CompletableFuture<List<Video>> getAllFromUser(final UUID userId) {
    simulateSlowService();
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return videoRepo.findAllByUser(userId);

    });
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

  public CompletableFuture<Optional<Video>> deleteVideo(final UUID videoId) {
    Optional<Video> video = videoRepoPageable.findById(videoId);
    if(video.isPresent()) {
      return supplyAsync(() -> {
        System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
        videoRepo.delete(video.get());
        return video;
      });
    }
    else return completedFuture(Optional.empty());
  }
}
