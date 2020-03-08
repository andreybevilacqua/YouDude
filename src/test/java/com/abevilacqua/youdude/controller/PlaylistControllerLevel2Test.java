package com.abevilacqua.youdude.controller;

import com.abevilacqua.youdude.controller.level_2.PlaylistController_Level2;
import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.service.PlaylistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.abevilacqua.youdude.utils.ObjectHelper.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlaylistControllerLevel2Test {

  @Autowired
  private PlaylistService playlistService;

  private MockMvc mockMvc;

  private final String URL = "/level2/playlists";

  @BeforeEach
  void setup() {
    PlaylistController_Level2 playlistControllerLevel2 = new PlaylistController_Level2(playlistService);
    mockMvc = createMockMvc(playlistControllerLevel2);
  }

  @Test
  @DisplayName("Should find all playlists")
  void shouldFindAllPlaylists() throws Exception {
    mockMvc.perform(get(URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.[0].playlist_id").isNumber())
        .andExpect(jsonPath("$.content.[0].name").isString())
        .andExpect(jsonPath("$.content.[0].videos_id").isArray())
        .andExpect(jsonPath("$.content.[0].user_id").isNumber());
  }

  @Test
  @DisplayName("Should find all playlists from an user")
  void shouldFindAllPlaylistsFromUser() throws Exception {
    mockMvc.perform(get(URL + "/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.[0].playlist_id").isNumber())
        .andExpect(jsonPath("$.content.[0].name").isString())
        .andExpect(jsonPath("$.content.[0].videos_id").isArray())
        .andExpect(jsonPath("$.content.[0].user_id").isNumber());
  }

  @Test
  @DisplayName("Should update playlist")
  void shouldUpdatePlaylist() throws Exception {
    Optional<Playlist> optionalPlaylist = playlistService.getAllPlaylists().join().stream().findFirst();
    if(optionalPlaylist.isPresent()) {
      Playlist playlist = updateDefaultPlaylist(optionalPlaylist.get());
      mockMvc.perform(put(URL)
          .contentType(APPLICATION_JSON)
          .content(mapToJSON(playlist)))
          .andExpect(status().isOk())
          .andExpect(jsonPath("$.playlist_id").exists())
          .andExpect(jsonPath("$.playlist_id").isNumber())
          .andExpect(jsonPath("$.name").isString())
          .andExpect(jsonPath("$.name").value(playlist.getName()))
          .andExpect(jsonPath("$.videos_id").isArray())
          .andExpect(jsonPath("$.user_id").isNumber());
    }
  }
}
