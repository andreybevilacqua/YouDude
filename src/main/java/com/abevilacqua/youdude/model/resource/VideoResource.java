package com.abevilacqua.youdude.model.resource;

import com.abevilacqua.youdude.model.Category;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

@Getter
public class VideoResource extends RepresentationModel<VideoResource> {

  private final String name, subject;

  private final int duration;

  private final Category category;

  private final User user;

  // I decide to remove the User ID from the Resource, because it's a internal db info
  // and doesn't have to be exposed in the API. The link itself will give the correct data.
  public VideoResource(final Video video) {
    this.name = video.getName();
    this.subject = video.getSubject();
    this.duration = video.getDuration();
    this.category = video.getCategory();
    this.user = video.getUser();
  }

}
