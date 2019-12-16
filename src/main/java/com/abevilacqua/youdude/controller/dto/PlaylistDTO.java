package com.abevilacqua.youdude.controller.dto;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.Video;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class PlaylistDTO {

  private final long playlist_id;

  private final String name;

  private final List<Long> videos_id;

  private final long user_id;

  public static PlaylistDTO mapper(final Playlist playlist) {
    List videos = playlist
        .getVideos()
        .stream()
        .map(Video::getId)
        .collect(Collectors.toList());

    return PlaylistDTO.builder()
        .playlist_id(playlist.getId())
        .name(playlist.getName())
        .videos_id(videos)
        .user_id(playlist.getUser().getId())
        .build();
  }

}
