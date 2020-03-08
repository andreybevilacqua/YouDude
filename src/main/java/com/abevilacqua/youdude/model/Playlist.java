package com.abevilacqua.youdude.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Immutable
@AllArgsConstructor
public final class Playlist implements Comparable<Playlist> {

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

  public static Playlist newInstance(final String name,
                                     final User user,
                                     final List<Video> videos) {
    return new Playlist(name, user, videos);
  }

  public static Playlist newInstanceWithId(
      final long id,
      final String name,
      final List<Video> videos,
      final User user) {
    return new Playlist(id, name, videos, user);
  }

  @Override
  public String toString() {
    return "Id: " + id + ", Name: " + name;
  }

  @Override
  public int hashCode() {
    int result;
    result = Long.hashCode(id);
    result = 31 * result + name.hashCode();
    for(Video video : videos) { result = 31 * result + video.hashCode(); }
    return result;
  }

  @Override
  public int compareTo(Playlist o) {
    return String.CASE_INSENSITIVE_ORDER.compare(name, o.getName());
  }
}
