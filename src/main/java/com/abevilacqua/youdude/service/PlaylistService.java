package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.PlaylistRepo;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

  private PlaylistRepo playlistRepo;

  private UserService userService;

  public PlaylistService(PlaylistRepo repo, UserService userService) {
    this.playlistRepo = repo;
    this.userService = userService;
  }

  public List<Playlist> getAllPlaylists() {
    return playlistRepo.findAll();
  }

  public List getAllFromUser(long user_id) {
    Optional<User> userOptional = userService.getById(user_id);
    if(userOptional.isPresent()) return playlistRepo.findAllByUser(userOptional.get());
    else return Collections.EMPTY_LIST;
  }

  public Playlist createPlaylist(Playlist playlist) {
    return playlistRepo.save(playlist);
  }

  public Optional<Playlist> deletePlaylist(long playlist_id) {
    Optional<Playlist> playlist = playlistRepo.findById(playlist_id);
    if(playlist.isPresent()) {
      playlistRepo.delete(playlist.get());
      return playlist;
    } else return Optional.empty();
  }
}
