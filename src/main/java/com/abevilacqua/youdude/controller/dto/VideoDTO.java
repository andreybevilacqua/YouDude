package com.abevilacqua.youdude.controller.dto;

import com.abevilacqua.youdude.model.Category;
import com.abevilacqua.youdude.model.Video;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class VideoDTO {

  private final UUID videoId;

  private final String name;

  private final String subject;

  private final int duration;

  private final Category category;

  private final UUID userId;

  public static VideoDTO mapper(final Video video) {
    return VideoDTO.builder()
        .videoId(video.getId())
        .name(video.getName())
        .subject(video.getSubject())
        .duration(video.getDuration())
        .category(video.getCategory())
        .userId(video.getUser().getId())
        .build();
  }
}
