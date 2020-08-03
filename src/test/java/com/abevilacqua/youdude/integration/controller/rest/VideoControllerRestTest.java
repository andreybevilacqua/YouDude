package com.abevilacqua.youdude.integration.controller.rest;

import com.abevilacqua.youdude.controller.rest.VideoControllerRest;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.repo.jpa.PlaylistRepo;
import com.abevilacqua.youdude.repo.jpa.UserRepo;
import com.abevilacqua.youdude.repo.jpa.VideoRepo;
import com.abevilacqua.youdude.service.UserService;
import com.abevilacqua.youdude.service.VideoService;
import com.abevilacqua.youdude.service.security.SecurityService;
import com.abevilacqua.youdude.utils.ObjectHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.utils.DBInitializer.initDB;
import static com.abevilacqua.youdude.utils.ObjectHelper.mapToJSON;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class VideoControllerRestTest {

  @Autowired
  private VideoService videoService;

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private VideoRepo videoRepo;

  @Autowired
  private PlaylistRepo playlistRepo;

  @MockBean
  private SecurityService securityService;

  private MockMvc mockMvc;

  private final String URL = "/rest/videos";

  @BeforeEach
  void setup() {
    VideoControllerRest videoControllerLevel2 = new VideoControllerRest(videoService, securityService);
    mockMvc = ObjectHelper.createMockMvc(videoControllerLevel2);
    if(userRepo.findAll().size() == 0) initDB(userRepo, videoRepo, playlistRepo);
    doNothing().when(securityService).processClientRequest(anyString());
  }

  @Test
  @DisplayName("Should find all videos")
  void shouldFindAllVideos() throws Exception {
    mockMvc.perform(get(URL)
        .contentType(APPLICATION_JSON)
        .header("token", "a-valid-token"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.[0].videoId").exists())
        .andExpect(jsonPath("$.content.[0].videoId").isString())
        .andExpect(jsonPath("$.content.[0].name").isString())
        .andExpect(jsonPath("$.content.[0].subject").isString())
        .andExpect(jsonPath("$.content.[0].duration").isNumber())
        .andExpect(jsonPath("$.content.[0].category").isString())
        .andExpect(jsonPath("$.content.[0].userId").isString());
  }

  @Test
  @DisplayName("Should find all videos from an user")
  void shouldFindAllVideosFromUser() throws Exception {
    Optional<Video> first = videoRepo.findAll().stream().findFirst();
    assertTrue(first.isPresent());
    Video v = first.get();
    mockMvc.perform(get(URL + "/" + v.getId().toString())
        .contentType(APPLICATION_JSON)
        .header("token", "a-valid-token"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.videoId").value(v.getId().toString()))
        .andExpect(jsonPath("$.name").value(v.getName()))
        .andExpect(jsonPath("$.subject").value(v.getSubject()))
        .andExpect(jsonPath("$.duration").value(v.getDuration()))
        .andExpect(jsonPath("$.userId", is(v.getUser().getId().toString())));
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
          .content(mapToJSON(video))
          .header("token", "a-valid-token"))
          .andExpect(status().isCreated())
          .andExpect(jsonPath("$.videoId").exists())
          .andExpect(jsonPath("$.videoId").isString())
          .andExpect(jsonPath("$.name").isString())
          .andExpect(jsonPath("$.subject").isString())
          .andExpect(jsonPath("$.duration").isNumber())
          .andExpect(jsonPath("$.category").isString())
          .andExpect(jsonPath("$.userId").isString());
    }
  }

}
