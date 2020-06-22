package com.abevilacqua.youdude.integration.controller.rest;

import com.abevilacqua.youdude.controller.rest.PlaylistControllerRest;
import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.repo.jpa.PlaylistRepo;
import com.abevilacqua.youdude.repo.jpa.UserRepo;
import com.abevilacqua.youdude.repo.jpa.VideoRepo;
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

import static com.abevilacqua.youdude.utils.DBInitializer.initDB;
import static com.abevilacqua.youdude.utils.ObjectHelper.createMockMvc;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PlaylistControllerRestTest {

  @Autowired
  private PlaylistService playlistService;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private VideoRepo videoRepo;

  @Autowired
  private PlaylistRepo playlistRepo;

  private MockMvc mockMvc;

  private final String URL = "/rest/playlists";

  @BeforeEach
  void setup() {
    PlaylistControllerRest playlistControllerRestLevel2 = new PlaylistControllerRest(playlistService);
    mockMvc = createMockMvc(playlistControllerRestLevel2);
    if(userRepo.findAll().size() == 0) initDB(userRepo, videoRepo, playlistRepo);
  }

  @Test
  @DisplayName("Should find all playlists")
  void shouldFindAllPlaylists() throws Exception {
    mockMvc.perform(get(URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.[0].playlistId").exists())
        .andExpect(jsonPath("$.content.[0].playlistId").isString())
        .andExpect(jsonPath("$.content.[0].name").isString())
        .andExpect(jsonPath("$.content.[0].videosId").isArray())
        .andExpect(jsonPath("$.content.[0].userId").isString());
  }

  @Test
  @DisplayName("Should find playlist by id")
  void shouldFindPlaylistById() throws Exception {
    Optional<Playlist> first = playlistRepo.findAll().stream().findFirst();
    assertTrue(first.isPresent());
    Playlist p = first.get();
    mockMvc.perform(get(URL + "/" + p.getId())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.playlistId").value(p.getId().toString()))
        .andExpect(jsonPath("$.name").value(p.getName()))
        .andExpect(jsonPath("$.videosId").isArray())
        .andExpect(jsonPath("$.userId").value(p.getUser().getId().toString()));
  }

}
