package com.abevilacqua.youdude.model.resource;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
public class PlaylistResource extends RepresentationModel<PlaylistResource> {

  private final long id;

  private final String name;

  private final List<Video> videos;

  private final User user;

  public PlaylistResource(final Playlist playlist) {
    this.id = playlist.getId();
    this.name = playlist.getName();
    this.videos = playlist.getVideos();
    this.user = playlist.getUser();
  }
}
