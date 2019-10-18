package com.abevilacqua.youdude.controller.level_0;

import com.abevilacqua.youdude.service.ChannelService;
import com.abevilacqua.youdude.service.UserService;
import com.abevilacqua.youdude.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/youdude_level0")
public class YouDudeController {

  private UserService userService;

  private ChannelService channelService;

  private VideoService videoService;

  @Autowired
  public YouDudeController yoududeController(UserService userService,
                                             ChannelService channelService,
                                             VideoService videoService) {
    this.userService = userService;
    this.channelService = channelService;
    this.videoService = videoService;
  }

  @PostMapping
  public ResponseEntity yoududeService(@PathParam("action") String action,
                                       @PathParam("user_id") long user_id) {
    if(action.equals("getUsers")) {
      return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    else if (action.equals("getChannelsPerUser")) {
      return new ResponseEntity<>(channelService.getAllChannelsFromUser(user_id));
    }
  }
}
