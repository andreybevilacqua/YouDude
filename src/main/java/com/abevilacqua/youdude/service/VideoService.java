package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.Channel;
import com.abevilacqua.youdude.repo.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;

@Service
public class VideoService {

  private VideoRepo videoRepo;

  private ChannelService channelService;

  @Autowired
  public VideoService(VideoRepo videoRepo,
                      ChannelService channelService) {
    this.videoRepo = videoRepo;
    this.channelService = channelService;
  }

  public List getVideosFromChannel(long channel_id) {
    Optional<Channel> optionalChannel = channelService.getChannelById(channel_id);

    if(optionalChannel.isPresent()) return videoRepo.findAllByChannel(optionalChannel.get());
    else return EMPTY_LIST;
  }
}
