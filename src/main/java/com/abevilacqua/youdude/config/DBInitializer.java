package com.abevilacqua.youdude.config;

import com.abevilacqua.youdude.model.Category;
import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.PlaylistRepo;
import com.abevilacqua.youdude.repo.UserRepo;
import com.abevilacqua.youdude.repo.VideoRepo;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDate;
import java.util.stream.Stream;

public final class DBInitializer {

  public static ApplicationRunner initDB(UserRepo userRepo,
                                         PlaylistRepo playlistRepo,
                                         VideoRepo videoRepo) {
    return args -> {
      Stream.of(
          createUser("user-1"),
          createUser("user-2"),
          createUser("user-3"))
          .forEach(userRepo::save);

      Stream.of(
          createPlaylist("playlist-1", Category.COMEDY, userRepo.findById(1L).get()),
          createPlaylist("channel-14", Category.COMEDY, userRepo.findById(2L).get()))
          .forEach(playlistRepo::save);
    };
  }

  private static User createUser(String name) {
    return new User(name, LocalDate.now());
  }

  private static Playlist createPlaylist(String name, Category category, User user) {
    return new Playlist(name, category, user);
  }
}
