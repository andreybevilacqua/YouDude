package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.Channel;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.ChannelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;

@Service
public class ChannelService {

  private ChannelRepo channelRepo;

  private UserService userService;

  @Autowired
  public ChannelService(ChannelRepo channelRepo, UserService userService) {
    this.channelRepo = channelRepo;
    this.userService = userService;
  }

  public List getAllChannelsFromUser(long user_id) {
    Optional<User> optionalUser = userService.getById(user_id);

    if(optionalUser.isPresent()) return channelRepo.findAllByOwner(optionalUser.get());
    else return EMPTY_LIST;
  }

  Optional<Channel> getChannelById(long id) { return channelRepo.findById(id); }

  public Channel createChannel(Channel channel) {
    return channelRepo.save(channel);
  }
}
