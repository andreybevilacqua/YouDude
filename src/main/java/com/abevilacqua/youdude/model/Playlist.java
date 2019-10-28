package com.abevilacqua.youdude.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Playlist extends RepresentationModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "playlist_id")
  private long id;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "playlist")
  private List<Video> videos;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Playlist(String name, User user) {
    this.name = name;
    this.user = user;
  }

  public static Playlist playlistWithVideos(String name, User user, List<Video> videos) {
    return new Playlist(name, user, videos);
  }

  private Playlist(String name, User user, List<Video> videos) {
    this.name = name;
    this.user = user;
    this.videos = videos;
  }
}
