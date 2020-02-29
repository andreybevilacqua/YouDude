package com.abevilacqua.youdude.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Playlist {

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

  public Playlist(final String name,
                  final User user) {
    this.name = name;
    this.user = user;
  }

  public static Playlist playlistWithVideos(final String name,
                                            final User user,
                                            final List<Video> videos) {
    return new Playlist(name, user, videos);
  }

  private Playlist(final String name,
                   final User user,
                   final List<Video> videos) {
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
