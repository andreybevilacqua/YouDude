package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.repo.jpa.PlaylistRepo;
import com.abevilacqua.youdude.repo.pageable.PlaylistRepoPageable;
import lombok.extern.slf4j.Slf4j;
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
public class PlaylistService {

  private final PlaylistRepoPageable playlistRepoPageable;

  private final PlaylistRepo playlistRepo;

  public PlaylistService(PlaylistRepoPageable repo,
                         PlaylistRepo playlistRepo) {
    this.playlistRepoPageable = repo;
    this.playlistRepo = playlistRepo;
  }

  @Cacheable("getAllPlaylistsPageable")
  public CompletableFuture<Page<Playlist>> getAllPlaylists(final int page,
                                                           final int size,
                                                           final String sortBy) {
    return getAll(playlistRepoPageable, page, size, sortBy);
  }

  @Cacheable("getAllPlaylists")
  public CompletableFuture<List<Playlist>> getAllPlaylists() {
    simulateSlowService();
    System.out.println("Thread running getAllPlaylists service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return playlistRepo.findAll();
    });
  }

  @Cacheable("getAllFromUserPageable")
  public CompletableFuture<Page<Playlist>> getAllFromUser(final int page,
                                                          final int size,
                                                          final String sortBy,
                                                          final UUID userId) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    System.out.println("Thread running getAllFromUser pageable service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return playlistRepoPageable.findAllByUser(userId, pageable);
    });
  }

  @Cacheable("getAllFromUser")
  public CompletableFuture<List<Playlist>> getAllFromUser(final UUID userId) {
    simulateSlowService();
    System.out.println("Thread running getAllFromUser service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return playlistRepo.findAllByUser(userId);
    });
  }

  @Cacheable("getAllById")
  public CompletableFuture<Optional<Playlist>> getById(final UUID playlistId) {
    simulateSlowService();
    System.out.println("Thread running getById service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return playlistRepo.findById(playlistId);
    });
  }

  public CompletableFuture<Playlist> createPlaylist(final Playlist playlist) {
    System.out.println("Thread running createPlaylist service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return playlistRepo.save(playlist);
    });
  }

  public CompletableFuture<Optional<Playlist>> deletePlaylist(final UUID playlistId) {
    Optional<Playlist> playlist = playlistRepoPageable.findById(playlistId);
    System.out.println("Thread running deletePlaylist service: " + Thread.currentThread());
    if(playlist.isPresent()) {
      playlistRepo.delete(playlist.get());
      return supplyAsync(() -> {
        System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
        return playlist;
      });
    } else return completedFuture(Optional.empty());
  }
}
