package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.PlaylistRepo;
import org.springframework.cache.annotation.Cacheable;
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
public class PlaylistService {

  private PlaylistRepo playlistRepo;

  private UserService userService;

  public PlaylistService(PlaylistRepo repo, UserService userService) {
    this.playlistRepo = repo;
    this.userService = userService;
  }

  @Async
  @Cacheable
  public CompletableFuture<Page<Playlist>> getAllPlaylists(int page, int size, String sortBy) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return completedFuture(playlistRepo.findAll(pageable));
  }

  @Async
  @Cacheable
  public CompletableFuture<Page<Playlist>> getAllFromUser(int page, int size, String sortBy, long user_id) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    Optional<User> userOptional = getOptionalUser(user_id);
    return userOptional
        .map(u -> completedFuture(playlistRepo.findAllByUser(userOptional.get(), pageable)))
        .orElseGet(() -> completedFuture(Page.empty()));
  }

  @Async
  public CompletableFuture<Playlist> createPlaylist(Playlist playlist) {
    return completedFuture(playlistRepo.save(playlist));
  }

  @Async
  public CompletableFuture<Optional<Playlist>> deletePlaylist(long playlist_id) {
    Optional<Playlist> playlist = playlistRepo.findById(playlist_id);
    if(playlist.isPresent()) {
      playlistRepo.delete(playlist.get());
      return completedFuture(playlist);
    } else return completedFuture(Optional.empty());
  }

  private Optional<User> getOptionalUser(long user_id) {
    CompletableFuture<Optional<User>> user = userService.getById(user_id);
    return user.join();
  }
}
