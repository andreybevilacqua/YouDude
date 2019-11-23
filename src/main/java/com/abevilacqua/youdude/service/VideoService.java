package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.repo.VideoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;

@Service
public class VideoService {

  private VideoRepo videoRepo;

  private UserService userService;

  @Autowired
  public VideoService(VideoRepo videoRepo,
                      UserService userService) {
    this.videoRepo = videoRepo;
    this.userService = userService;
  }

  public List<Video> getAllVideos() {
    return videoRepo.findAll();
  }

  public List<Video> getAllFromUser(long user_id) {
    Optional<User> optionalUser = userService.getById(user_id);

    if(optionalUser.isPresent()) return videoRepo.findAllByUser(optionalUser.get());
    else return EMPTY_LIST;
  }

  public Video createVideo(Video video) {
    return videoRepo.save(video);
  }

  public Optional<Video> deleteVideo(long video_id) {
    Optional<Video> video = videoRepo.findById(video_id);
    if(video.isPresent()) {
      videoRepo.delete(video.get());
      return video;
    }
    else return Optional.empty();
  }

  public Optional<Video> updateVideo(Video video) {
    Optional<Video> optVideo = videoRepo.findById(video.getId());
    if(optVideo.isPresent()) {
      Video tempVideo = optVideo.get();
      tempVideo.setName(video.getName());
      tempVideo.setDuration(video.getDuration());
      tempVideo.setUser(video.getUser());
      tempVideo.setSubject(video.getSubject());
      return Optional.of(videoRepo.save(tempVideo));
    }
    else return Optional.empty();
  }
}
