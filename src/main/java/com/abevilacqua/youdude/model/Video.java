package com.abevilacqua.youdude.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
public class Video {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name")
  @NotEmpty
  @Setter
  private String name;

  @Column(name = "subject")
  @NotEmpty
  @Setter
  private String subject;

  @Column(name = "duration")
  @NotNull
  @Setter
  private int duration;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @Setter
  private User user;

  @ManyToOne
  @JoinColumn(name = "playlist_id")
  @Setter
  private Playlist playlist;

  public Video(String name, String subject, int duration) {
    this.name = name;
    this.subject = subject;
    this.duration = duration;
  }

  public static Video VideoWithPlaylist(String name, String subject, int duration, Playlist playlist) {
    return new Video(name, subject, duration, playlist);
  }

  private Video(String name, String subject, int duration, Playlist playlist) {
    this.name = name;
    this.subject = subject;
    this.duration = duration;
    this.playlist = playlist;
  }
}
