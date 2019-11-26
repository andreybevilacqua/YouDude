package com.abevilacqua.youdude.controller;

import com.abevilacqua.youdude.controller.level_2.PlaylistController;
import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.abevilacqua.youdude.service.PlaylistService;
import com.abevilacqua.youdude.service.UserService;
import com.abevilacqua.youdude.service.VideoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.abevilacqua.youdude.utils.ObjectHelper.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlaylistControllerTest {

  @Autowired
  private PlaylistService playlistService;

  @Autowired
  private UserService userService;

  @Autowired
  private VideoService videoService;

  private MockMvc mockMvc;

  private final String URL = "/level2/playlists";

  @BeforeEach
  void setup() {
    PlaylistController playlistController = new PlaylistController(playlistService);
    mockMvc = createMockMvc(playlistController);
  }

  @Test
  @DisplayName("Should find all playlists")
  void shouldFindAllPlaylists() throws Exception {
    mockMvc.perform(get(URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].playlist_id").isNumber())
        .andExpect(jsonPath("$[0].name").isString())
        .andExpect(jsonPath("$[0].videos_id").isArray())
        .andExpect(jsonPath("$[0].user_id").isNumber());
  }

  @Test
  @DisplayName("Should find all playlists from an user")
  void shouldFindAllPlaylistsFromUser() throws Exception {
    mockMvc.perform(get(URL + "/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].playlist_id").isNumber())
        .andExpect(jsonPath("$[0].name").isString())
        .andExpect(jsonPath("$[0].videos_id").isArray())
        .andExpect(jsonPath("$[0].user_id").isNumber());
  }
}
