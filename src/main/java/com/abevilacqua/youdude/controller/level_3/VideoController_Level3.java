package com.abevilacqua.youdude.controller.level_3;

import com.abevilacqua.youdude.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/level3/videos", produces = "application/hal+json")
public class VideoController_Level3 {

  private VideoService videoService;

  @Autowired
  public VideoController_Level3(final VideoService videoService) {
    this.videoService = videoService;
  }
}
