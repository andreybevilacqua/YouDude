package com.abevilacqua.youdude.config;

import com.abevilacqua.youdude.model.Category;
import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.repo.PlaylistRepoPageable;
import com.abevilacqua.youdude.repo.UserRepoPageable;
import com.abevilacqua.youdude.repo.VideoRepoPageable;
import org.springframework.boot.ApplicationRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static com.abevilacqua.youdude.model.Playlist.playlistWithVideos;

public final class DBInitializer {

  public static ApplicationRunner initDB(UserRepoPageable userRepoPageable,
                                         PlaylistRepoPageable playlistRepoPageable,
                                         VideoRepoPageable videoRepoPageable) {
    User user1 = new User("user-1", LocalDate.now());
    User user2 = new User("user-2", LocalDate.now());
    User user3 = new User("user-3", LocalDate.now());

    return args -> {
      Stream.of(user1, user2, user3)
          .forEach(userRepoPageable::save);

      Stream.of(
          createVideo("video-1", "subject 1", 10, Category.COMEDY, user1),
          createVideo("video-2", "subject 2", 15, Category.EDUCATIONAL, user1),
          createVideo("video-3", "subject 3", 20, Category.VLOGS, user1),
          createVideo("video-4", "subject 4", 25, Category.UNBOXING, user1),
          createVideo("video-5", "subject 5", 30, Category.HOW_TO, user1),
          createVideo("video-6", "subject 6", 35, Category.BEST_OF, user1),
          createVideo("video-7", "subject 7", 37, Category.GAMING, user2),
          createVideo("video-8", "subject 8", 40, Category.COMEDY, user2),
          createVideo("video-9", "subject 9", 44, Category.EDUCATIONAL, user2),
          createVideo("video-10", "subject 10", 39, Category.UNBOXING, user2),
          createVideo("video-11", "subject 11", 17, Category.HOW_TO, user3),
          createVideo("video-12", "subject 11", 8, Category.BEST_OF, user3),
          createVideo("video-13", "subject 7", 37, Category.GAMING, user3),
          createVideo("video-14", "subject 3", 20, Category.COMEDY, user3),
          createVideo("video-15", "subject 3", 20, Category.BEST_OF, user3),
          createVideo("video-16", "subject 3", 20, Category.UNBOXING, user3),
          createVideo("video-17", "subject 3", 20, Category.BEST_OF, user3),
          createVideo("video-18", "subject 3", 20, Category.HOW_TO, user3),
          createVideo("video-19", "subject 3", 20, Category.UNBOXING, user3),
          createVideo("video-20", "subject 3", 20, Category.GAMING, user3),
          createVideo("video-21", "subject 3", 20, Category.VLOGS, user3),
          createVideo("video-22", "subject 3", 20, Category.COMEDY, user3),
          createVideo("video-23", "subject 3", 20, Category.COMEDY, user3),
          createVideo("video-24", "subject 3", 20, Category.VLOGS, user3),
          createVideo("video-25", "subject 3", 20, Category.EDUCATIONAL, user3),
          createVideo("video-26", "subject 3", 20, Category.VLOGS, user3),
          createVideo("video-27", "subject 3", 20, Category.EDUCATIONAL, user3),
          createVideo("video-28", "subject 3", 20, Category.HOW_TO, user3))
          .forEach(videoRepoPageable::save);

      List<Video> comedyList = videoRepoPageable.findAllByCategory(Category.COMEDY);
      List<Video> educationalList = videoRepoPageable.findAllByCategory(Category.EDUCATIONAL);
      List<Video> vlogList = videoRepoPageable.findAllByCategory(Category.VLOGS);
      List<Video> unboxingList = videoRepoPageable.findAllByCategory(Category.UNBOXING);
      List<Video> howToList = videoRepoPageable.findAllByCategory(Category.HOW_TO);
      List<Video> gamingList = videoRepoPageable.findAllByCategory(Category.GAMING);
      List<Video> bestOfList = videoRepoPageable.findAllByCategory(Category.BEST_OF);

      Stream.of(
          playlistWithVideos("playlist-1", user1, comedyList),
          playlistWithVideos("playlist-2", user1, educationalList),
          playlistWithVideos("playlist-3", user2, vlogList),
          playlistWithVideos("playlist-4", user2, unboxingList),
          playlistWithVideos("playlist-5", user3, howToList),
          playlistWithVideos("playlist-6", user3, gamingList),
          playlistWithVideos("playlist-7", user3, bestOfList),
          createPlaylist("playlist-8", user1),
          createPlaylist("playlist-9", user1),
          createPlaylist("playlist-10", user1))
          .forEach(playlistRepoPageable::save);
    };
  }

  private static Video createVideo(String name, String subject, int duration, Category category, User user){
    return new Video(name, subject, duration, category, user);
  }

  private static Playlist createPlaylist(String name, User user) {
    return new Playlist(name, user);
  }

}
