package com.abevilacqua.youdude.controller.level_3;

import com.abevilacqua.youdude.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/level3/playlists", produces = "application/hal+json")
public class PlaylistController_Level3 {

  private PlaylistService playlistService;

  @Autowired
  public PlaylistController_Level3(final PlaylistService playlistService) {
    this.playlistService = playlistService;
  }
}
