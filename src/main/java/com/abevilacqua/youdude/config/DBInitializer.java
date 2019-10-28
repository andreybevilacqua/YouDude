package com.abevilacqua.youdude.config;

import com.abevilacqua.youdude.model.Category;
import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.repo.PlaylistRepo;
import com.abevilacqua.youdude.repo.UserRepo;
import com.abevilacqua.youdude.repo.VideoRepo;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDate;
import java.util.List;
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
        createVideo("video-1", "subject 1", 10, Category.COMEDY, userRepo.findById(1L).get()),
          createVideo("video-2", "subject 2", 15, Category.EDUCATIONAL, userRepo.findById(1L).get()),
          createVideo("video-3", "subject 3", 20, Category.VLOGS, userRepo.findById(1L).get()),
          createVideo("video-4", "subject 4", 25, Category.UNBOXING, userRepo.findById(1L).get()),
          createVideo("video-5", "subject 5", 30, Category.HOW_TO, userRepo.findById(1L).get()),
          createVideo("video-6", "subject 6", 35, Category.BEST_OF, userRepo.findById(1L).get()),
          createVideo("video-7", "subject 7", 37, Category.GAMING, userRepo.findById(2L).get()),
          createVideo("video-8", "subject 8", 40, Category.COMEDY, userRepo.findById(2L).get()),
          createVideo("video-9", "subject 9", 44, Category.EDUCATIONAL, userRepo.findById(2L).get()),
          createVideo("video-10", "subject 10", 39, Category.UNBOXING, userRepo.findById(2L).get()),
          createVideo("video-11", "subject 11", 17, Category.HOW_TO, userRepo.findById(3L).get()),
          createVideo("video-12", "subject 11", 8, Category.BEST_OF, userRepo.findById(3L).get()),
          createVideo("video-13", "subject 7", 37, Category.GAMING, userRepo.findById(2L).get()),
          createVideo("video-14", "subject 3", 20, Category.VLOGS, userRepo.findById(1L).get()))
          .forEach(videoRepo::save);

      Stream.of(
          playlistWithVideos("playlist-1", userRepo.findById(1L).get(), videoRepo.findAllByCategory(Category.COMEDY)),
          playlistWithVideos("playlist-2", userRepo.findById(1L).get(), videoRepo.findAllByCategory(Category.EDUCATIONAL)),
          playlistWithVideos("playlist-3", userRepo.findById(2L).get(), videoRepo.findAllByCategory(Category.VLOGS)),
          playlistWithVideos("playlist-4", userRepo.findById(2L).get(), videoRepo.findAllByCategory(Category.UNBOXING)),
          playlistWithVideos("playlist-5", userRepo.findById(3L).get(), videoRepo.findAllByCategory(Category.HOW_TO)),
          playlistWithVideos("playlist-6", userRepo.findById(3L).get(), videoRepo.findAllByCategory(Category.GAMING)),
          playlistWithVideos("playlist-7", userRepo.findById(3L).get(), videoRepo.findAllByCategory(Category.BEST_OF)),
          createPlaylist("playlist-8", userRepo.findById(3L).get()))
          .forEach(playlistRepo::save);
    };
  }

  private static User createUser(String name) {
    return new User(name, LocalDate.now());
  }

  private static Video createVideo(String name, String subject, int duration, Category category, User user){
    return new Video(name, subject, duration, category, user);
  }

  private static Playlist createPlaylist(String name, User user) {
    return new Playlist(name, user);
  }

  private static Playlist playlistWithVideos(String name, User user, List<Video> videos) {
    return Playlist.playlistWithVideos(name, user, videos);
  }
}
