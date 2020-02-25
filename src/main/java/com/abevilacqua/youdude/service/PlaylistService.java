package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.jpa.PlaylistRepo;
import com.abevilacqua.youdude.repo.pageable.PlaylistRepoPageable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.service.helper.ServiceHelper.simulateSlowService;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@Service
public class PlaylistService {

  private PlaylistRepoPageable playlistRepoPageable;

  private PlaylistRepo playlistRepo;

  private UserService userService;

  public PlaylistService(final PlaylistRepoPageable repo,
                         final UserService userService,
                         final PlaylistRepo playlistRepo) {
    this.playlistRepoPageable = repo;
    this.userService = userService;
    this.playlistRepo = playlistRepo;
  }

  @Async
  @Cacheable("getAllPlaylistsPageable")
  public CompletableFuture<Page<Playlist>> getAllPlaylists(final int page,
                                                           final int size,
                                                           final String sortBy) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    System.out.println("Thread running getAllPlaylists pageable service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return playlistRepoPageable.findAll(pageable);
    });
  }

  @Async
  @Cacheable("getAllPlaylists")
  public CompletableFuture<List<Playlist>> getAllPlaylists() {
    simulateSlowService();
    System.out.println("Thread running getAllPlaylists service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return playlistRepo.findAll();
    });
  }

  @Async
  @Cacheable("getAllFromUserPageable")
  public CompletableFuture<Page<Playlist>> getAllFromUser(final int page,
                                                          final int size,
                                                          final String sortBy,
                                                          final long user_id) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    Optional<User> userOptional = getOptionalUser(user_id);
    System.out.println("Thread running getAllFromUser pageable service: " + Thread.currentThread());
    return userOptional
        .map(u -> supplyAsync(() -> {
          System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
          return playlistRepoPageable.findAllByUser(userOptional.get(), pageable);
        }))
        .orElseGet(() -> completedFuture((Page.empty())));
  }

  @Async
  @Cacheable("getAllFromUser")
  public CompletableFuture<List<Playlist>> getAllFromUser(final long user_id) {
    simulateSlowService();
    Optional<User> userOptional = getOptionalUser(user_id);
    System.out.println("Thread running getAllFromUser service: " + Thread.currentThread());
    return userOptional
        .map(user -> supplyAsync(() -> {
          System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
          return playlistRepo.findAllByUser(user);
        }))
        .orElseGet(() -> completedFuture(new ArrayList<>()));
  }

  @Async
  @Cacheable("getAllById")
  public CompletableFuture<Optional<Playlist>> getById(final long playlist_id) {
    simulateSlowService();
    System.out.println("Thread running getById service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return playlistRepo.findById(playlist_id);
    });
  }

  @Async
  public CompletableFuture<Playlist> createPlaylist(final Playlist playlist) {
    System.out.println("Thread running createPlaylist service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return playlistRepoPageable.save(playlist);
    });
  }

  @Async
  public CompletableFuture<Optional<Playlist>> deletePlaylist(final long playlist_id) {
    Optional<Playlist> playlist = playlistRepoPageable.findById(playlist_id);
    System.out.println("Thread running deletePlaylist service: " + Thread.currentThread());
    if(playlist.isPresent()) {
      playlistRepoPageable.delete(playlist.get());
      return supplyAsync(() -> {
        System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
        return playlist;
      });
    } else return completedFuture(Optional.empty());
  }

  private Optional<User> getOptionalUser(final long user_id) {
    System.out.println("Thread running getOptionalUser service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return userService.getById(user_id);
    }).join().join();
  }
}
