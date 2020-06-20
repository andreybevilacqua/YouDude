package com.abevilacqua.youdude.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public final class Playlist implements Comparable<Playlist> {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "playlist_id")
  private UUID id;

  @Column(name = "name")
  private String name;

  @OneToMany(mappedBy = "playlist", fetch = FetchType.EAGER)
  private List<Video> videos;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public static Playlist newInstance(final String name,
                                     final User user,
                                     final List<Video> videos) {
    Objects.requireNonNull(name, "Name should not be null");
    Objects.requireNonNull(name, "User should not be null");
    if(videos == null) return new Playlist(name, user, new ArrayList<>());
    else return new Playlist(name, user, videos);
  }

  private Playlist(final String name, final User user, final List<Video> videos) {
    this.name = name;
    this.user = user;
    this.videos = videos;
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
