package com.abevilacqua.youdude.controller.dto;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.Video;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
public class PlaylistDTO {

  private final UUID playlist_id;

  private final String name;

  private final List<UUID> videos_id;

  private final UUID user_id;

  public static PlaylistDTO mapper(final Playlist playlist) {
    List<UUID> videos = playlist
        .getVideos()
        .stream()
        .map(Video::getId)
        .collect(Collectors.toList());

    return PlaylistDTO
        .builder()
        .playlist_id(playlist.getId())
        .name(playlist.getName())
        .videos_id(videos)
        .user_id(playlist.getUser().getId())
        .build();
  }

}
