package com.abevilacqua.youdude.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
  @NotEmpty
  private String name;

  @Column(name = "category")
  @NotEmpty
  private Category category;

  @OneToMany(mappedBy = "playlist", cascade = CascadeType.ALL)
  private List<Video> videos;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Playlist(String name, Category category, User user) {
    this.name = name;
    this.category = category;
    this.user = user;
  }

  public static Playlist playlistWithVideos(String name, Category category, User user, List<Video> videos) {
    return new Playlist(name, category, user, videos);
  }

  private Playlist(String name, Category category, User user, List<Video> videos) {
    this.name = name;
    this.category = category;
    this.user = user;
    this.videos = videos;
  }
}
