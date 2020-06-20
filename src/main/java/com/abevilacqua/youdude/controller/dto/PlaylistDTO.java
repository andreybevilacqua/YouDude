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

  private final UUID playlistId;

  private final String name;

  private final List<UUID> videosId;

  private final UUID userId;

  public static PlaylistDTO mapper(final Playlist playlist) {
    List<UUID> videos = playlist
        .getVideos()
        .stream()
        .map(Video::getId)
        .collect(Collectors.toList());

    return PlaylistDTO
        .builder()
        .playlistId(playlist.getId())
        .name(playlist.getName())
        .videosId(videos)
        .userId(playlist.getUser().getId())
        .build();
  }

}
