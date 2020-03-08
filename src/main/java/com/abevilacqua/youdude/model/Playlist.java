package com.abevilacqua.youdude.model;

import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Immutable
public final class Playlist {

  private int hashCode;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "playlist_id")
  private long id;

  @Column(name = "name")
  private String name;

  @OneToMany(fetch = FetchType.EAGER)
  private List<Video> videos;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  Playlist(){}

  Playlist(final String name, final User user, final List<Video> videos) {
    this.name = name;
    this.user = user;
    this.videos = videos;
  }

  Playlist(final long id, final String name, final User user, final List<Video> videos) {
    this.id = id;
    this.name = name;
    this.user = user;
    this.videos = videos;
  }

  public static Playlist newInstance(final String name,
                                     final User user,
                                     final List<Video> videos) {
    return new Playlist(name, user, videos);
  }

  public static Playlist newInstanceWithId(
      final long id,
      final String name,
      final User user,
      final List<Video> videos) {
    return new Playlist(id, name, user, videos);
  }

  @Override
  public String toString() {
    return "Id: " + id + ", Name: " + name;
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if(result == 0) {
      result = Long.hashCode(id);
      result = 31 * result + name.hashCode();
      for(Video video : videos) { result = 31 * result + video.hashCode(); }
      hashCode = result;
    }
    return result;
  }
}
