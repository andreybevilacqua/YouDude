package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.repo.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {

  private VideoRepo videoRepo;

  @Autowired
  public VideoService(VideoRepo videoRepo) { this.videoRepo = videoRepo; }

  public List<Video> getAllVideos() {
    return videoRepo.findAll();
  }
}
