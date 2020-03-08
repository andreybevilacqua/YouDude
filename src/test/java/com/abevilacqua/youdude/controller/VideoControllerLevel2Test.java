package com.abevilacqua.youdude.controller;

import com.abevilacqua.youdude.controller.level_2.VideoController_Level2;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.service.UserService;
import com.abevilacqua.youdude.service.VideoService;
import com.abevilacqua.youdude.utils.ObjectHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.utils.ObjectHelper.mapToJSON;
import static com.abevilacqua.youdude.utils.ObjectHelper.updateDefaultVideo;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class VideoControllerLevel2Test {

  @Autowired
  private VideoService videoService;

  @Autowired
  private UserService userService;

  private MockMvc mockMvc;

  private final String URL = "/level2/videos";

  @BeforeEach
  void setup() {
    VideoController_Level2 videoControllerLevel2 = new VideoController_Level2(videoService);
    mockMvc = ObjectHelper.createMockMvc(videoControllerLevel2);
  }

  @Test
  @DisplayName("Should find all videos")
  void shouldFindAllVideos() throws Exception {
    mockMvc.perform(get(URL)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.[0].video_id").exists())
        .andExpect(jsonPath("$.content.[0].video_id").isNumber())
        .andExpect(jsonPath("$.content.[0].name").isString())
        .andExpect(jsonPath("$.content.[0].subject").isString())
        .andExpect(jsonPath("$.content.[0].duration").isNumber())
        .andExpect(jsonPath("$.content.[0].category").isString())
        .andExpect(jsonPath("$.content.[0].user_id").isNumber());
  }

  @Test
  @DisplayName("Should find all videos from an user")
  void shouldFindAllVideosFromUser() throws Exception {
    mockMvc.perform(get(URL + "/4")
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.video_id").exists())
        .andExpect(jsonPath("$.video_id").isNumber())
        .andExpect(jsonPath("$.name").isString())
        .andExpect(jsonPath("$.subject").isString())
        .andExpect(jsonPath("$.duration").isNumber())
        .andExpect(jsonPath("$.category").isString())
        .andExpect(jsonPath("$.user_id", is(1)));
  }

  @Test
  @DisplayName("Should create a new video")
  void shouldCreateVideo() throws Exception {
    CompletableFuture<Page<User>> completableFuture = userService.getAllUsers(0, 10, "name");
    Optional<User> user = completableFuture.join().stream().findFirst();
    if(user.isPresent()) {
      Video video = ObjectHelper.createDefaultVideo(user.get());
      mockMvc.perform(post(URL)
          .contentType(APPLICATION_JSON)
          .content(mapToJSON(video)))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.video_id").exists())
          .andExpect(jsonPath("$.video_id").isNumber())
          .andExpect(jsonPath("$.name").isString())
          .andExpect(jsonPath("$.subject").isString())
          .andExpect(jsonPath("$.duration").isNumber())
          .andExpect(jsonPath("$.category").isString())
          .andExpect(jsonPath("$.user_id", is(1)));
    }
  }

  @Test
  @DisplayName("Should update a new video")
  void shouldUpdateVideo() throws Exception {
    Optional<Video> optionalVideo = videoService.getAllVideos().join().stream().findFirst();
    if(optionalVideo.isPresent()) {
      Video video = updateDefaultVideo(optionalVideo.get());
      mockMvc.perform(put(URL)
          .contentType(APPLICATION_JSON)
          .content(mapToJSON(video)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.video_id").exists())
          .andExpect(jsonPath("$.video_id").isNumber())
          .andExpect(jsonPath("$.name").isString())
          .andExpect(jsonPath("$.name").value(video.getName()))
          .andExpect(jsonPath("$.subject").isString())
          .andExpect(jsonPath("$.duration").isNumber())
          .andExpect(jsonPath("$.category").isString())
          .andExpect(jsonPath("$.user_id", is(1)));
    }
  }
}
