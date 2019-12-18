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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.service.helper.ServiceHelper.simulateSlowService;
import static java.util.Collections.EMPTY_LIST;
import static java.util.concurrent.CompletableFuture.completedFuture;

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
    return completedFuture(playlistRepoPageable.findAll(pageable));
  }

  @Async
  @Cacheable("getAllPlaylists")
  public CompletableFuture<List<Playlist>> getAllPlaylists() {
    simulateSlowService();
    return completedFuture(playlistRepo.findAll());
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
    return userOptional
        .map(u -> completedFuture(playlistRepoPageable.findAllByUser(userOptional.get(), pageable)))
        .orElseGet(() -> completedFuture(Page.empty()));
  }

  @Async
  @Cacheable("getAllFromUser")
  public CompletableFuture<List<Playlist>> getAllFromUser(final long user_id) {
    simulateSlowService();
    Optional<User> userOptional = getOptionalUser(user_id);
    return userOptional
        .map(user -> completedFuture(playlistRepo.findAllByUser(user)))
        .orElseGet(() -> completedFuture(EMPTY_LIST));
  }

  @Async
  @Cacheable("getAllById")
  public CompletableFuture<Optional<Playlist>> getAllById(final long playlist_id) {
    simulateSlowService();
    return completedFuture(playlistRepo.findById(playlist_id));
  }

  @Async
  public CompletableFuture<Playlist> createPlaylist(final Playlist playlist) {
    return completedFuture(playlistRepoPageable.save(playlist));
  }

  @Async
  public CompletableFuture<Optional<Playlist>> deletePlaylist(final long playlist_id) {
    Optional<Playlist> playlist = playlistRepoPageable.findById(playlist_id);
    if(playlist.isPresent()) {
      playlistRepoPageable.delete(playlist.get());
      return completedFuture(playlist);
    } else return completedFuture(Optional.empty());
  }

  private Optional<User> getOptionalUser(final long user_id) {
    CompletableFuture<Optional<User>> user = userService.getById(user_id);
    return user.join();
  }
}
