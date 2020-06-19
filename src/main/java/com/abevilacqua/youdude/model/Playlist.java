package com.abevilacqua.youdude.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class Playlist implements Comparable<Playlist> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "playlist_id")
  private UUID id;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "playlist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<Video> videos;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private User user;

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
      final UUID id,
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
    result = id.toString().hashCode();
    result = 31 * result + name.hashCode();
    for(Video video : videos) { result = 31 * result + video.hashCode(); }
    return result;
  }

  @Override
  public int compareTo(Playlist o) {
    return String.CASE_INSENSITIVE_ORDER.compare(name, o.getName());
  }
}
