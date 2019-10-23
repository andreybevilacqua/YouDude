package com.abevilacqua.youdude.controller.level_0;

import com.abevilacqua.youdude.model.Channel;
import com.abevilacqua.youdude.service.ChannelService;
import com.abevilacqua.youdude.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

  @PostMapping
  public ResponseEntity yoududeService(@RequestParam(value = "action") String action,
                                       @RequestParam(value = "user_id", required = false) long user_id,
                                       @RequestBody(required = false) Channel channel) {
    switch (action) {
      case "getUsers":
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
      case "getChannelsPerUser":
        return new ResponseEntity<>(channelService.getAllChannelsFromUser(user_id), HttpStatus.OK);
      case "createChannel":
        return new ResponseEntity<>(channelService.createChannel(channel), HttpStatus.CREATED);
      default:
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
