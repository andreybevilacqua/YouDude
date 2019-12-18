package com.abevilacqua.youdude.model.resource;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
public class PlaylistResource extends RepresentationModel<PlaylistResource> {

  private final String name;

  private final List<Video> videos;

  private final User user;

  // I decide to remove the User ID from the Resource, because it's a internal db info
  // and doesn't have to be exposed in the API. The link itself will give the correct data.
  public PlaylistResource(final Playlist playlist) {
    this.name = playlist.getName();
    this.videos = playlist.getVideos();
    this.user = playlist.getUser();
  }
}
