package com.abevilacqua.youdude.controller.level_0;

import com.abevilacqua.youdude.service.ChannelService;
import com.abevilacqua.youdude.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/youdude_level0")
public class YouDudeController {

  private UserService userService;

  private ChannelService channelService;

  @Autowired
  public YouDudeController(UserService userService,
                           ChannelService channelService) {
    this.userService = userService;
    this.channelService = channelService;
  }
}
