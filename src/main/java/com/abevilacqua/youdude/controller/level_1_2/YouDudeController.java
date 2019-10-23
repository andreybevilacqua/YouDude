package com.abevilacqua.youdude.controller.level_1_2;

import com.abevilacqua.youdude.model.Channel;
import com.abevilacqua.youdude.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/youdude_level1_2")
public class YouDudeController {

  private ChannelService channelService;

  @Autowired
  public YouDudeController(ChannelService channelService) {
    this.channelService = channelService;
  }

  @GetMapping
  public ResponseEntity getChannelsPerUser(@RequestParam(value = "user_id") long user_id) {
    return new ResponseEntity<>(channelService.getAllChannelsFromUser(user_id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity createChannel(@RequestBody Channel channel) {
    return new ResponseEntity<>(channelService.createChannel(channel), HttpStatus.CREATED);
  }
}
