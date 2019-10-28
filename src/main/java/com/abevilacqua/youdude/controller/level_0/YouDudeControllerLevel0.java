package com.abevilacqua.youdude.controller.level_0;

import com.abevilacqua.youdude.service.VideoService;
import com.abevilacqua.youdude.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youdude_level0")
public class YouDudeControllerLevel0 {

  private UserService userService;

  private VideoService playlistService;

  @Autowired
  public YouDudeControllerLevel0(UserService userService,
                                 VideoService playlistService) {
    this.userService = userService;
    this.playlistService = playlistService;
  }
}
