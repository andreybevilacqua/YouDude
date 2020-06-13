package com.abevilacqua.youdude.utils;

import com.abevilacqua.youdude.model.Category;
import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.repo.jpa.PlaylistRepo;
import com.abevilacqua.youdude.repo.jpa.UserRepo;
import com.abevilacqua.youdude.repo.jpa.VideoRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.abevilacqua.youdude.model.User.newInstance;
import static com.abevilacqua.youdude.model.Video.newInstance;

public final class DBInitializer {

  public static void initDB(UserRepo userRepo,
                            VideoRepo videoRepo,
                            PlaylistRepo playlistRepo) {
    User user1 = newInstance("user-1", LocalDate.now());
    User user2 = newInstance("user-2", LocalDate.now());
    User user3 = newInstance("user-3", LocalDate.now());

    createUsers(userRepo, user1, user2, user3);
    createVideos(videoRepo, user1, user2, user3);
    createPlaylists(playlistRepo, videoRepo, user1, user2, user3);
  }

  private static void createUsers(UserRepo userRepo,
                                  User user1,
                                  User user2,
                                  User user3) {
    Stream
        .of(user1, user2, user3)
        .forEach(userRepo::save);
  }

  private static void createVideos(VideoRepo videoRepo,
                                   User user1,
                                   User user2,
                                   User user3) {
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
        .forEach(videoRepo::save);
  }

  private static Video createVideo(String name,
                                   String subject,
                                   int duration,
                                   Category category,
                                   User user){
    return newInstance(name, subject, duration, category, user);
  }

  private static void createPlaylists(PlaylistRepo playlistRepo,
                                      VideoRepo videoRepo,
                                      User user1,
                                      User user2,
                                      User user3) {
    List<Video> comedyList = videoRepo.findAllByCategory(Category.COMEDY);
    List<Video> educationalList = videoRepo.findAllByCategory(Category.EDUCATIONAL);
    List<Video> vlogList = videoRepo.findAllByCategory(Category.VLOGS);
    List<Video> unboxingList = videoRepo.findAllByCategory(Category.UNBOXING);
    List<Video> howToList = videoRepo.findAllByCategory(Category.HOW_TO);
    List<Video> gamingList = videoRepo.findAllByCategory(Category.GAMING);
    List<Video> bestOfList = videoRepo.findAllByCategory(Category.BEST_OF);

    Stream.of(
        Playlist.newInstance("playlist-1", user1, comedyList),
        Playlist.newInstance("playlist-2", user1, educationalList),
        Playlist.newInstance("playlist-3", user2, vlogList),
        Playlist.newInstance("playlist-4", user2, unboxingList),
        Playlist.newInstance("playlist-5", user3, howToList),
        Playlist.newInstance("playlist-6", user3, gamingList),
        Playlist.newInstance("playlist-7", user3, bestOfList),
        Playlist.newInstance("playlist-8", user1, new ArrayList<>()),
        Playlist.newInstance("playlist-9", user1, new ArrayList<>()),
        Playlist.newInstance("playlist-10", user1, new ArrayList<>()))
        .forEach(playlistRepo::save);
  }

}
