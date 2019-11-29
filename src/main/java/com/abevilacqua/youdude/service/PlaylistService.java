package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.PlaylistRepo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

@Service
public class PlaylistService {

  private PlaylistRepo playlistRepo;

  private UserService userService;

  public PlaylistService(PlaylistRepo repo, UserService userService) {
    this.playlistRepo = repo;
    this.userService = userService;
  }

  public CompletableFuture<List<Playlist>> getAllPlaylists() {
    return completedFuture(playlistRepo.findAll());
  }

  public CompletableFuture<List<Playlist>> getAllFromUser(long user_id) {
    CompletableFuture<Optional<User>> user = userService.getById(user_id);
    Optional<User> userOptional = user.join();
    return userOptional
        .map(u -> completedFuture(playlistRepo.findAllByUser(userOptional.get())))
        .orElseGet(() -> completedFuture(Collections.EMPTY_LIST));
  }

  public CompletableFuture<Playlist> createPlaylist(Playlist playlist) {
    return completedFuture(playlistRepo.save(playlist));
  }

  public CompletableFuture<Optional<Playlist>> deletePlaylist(long playlist_id) {
    Optional<Playlist> playlist = playlistRepo.findById(playlist_id);
    if(playlist.isPresent()) {
      playlistRepo.delete(playlist.get());
      return completedFuture(playlist);
    } else return completedFuture(Optional.empty());
  }
}
