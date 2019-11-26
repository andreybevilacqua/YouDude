package com.abevilacqua.youdude.utils;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static com.abevilacqua.youdude.model.Category.COMEDY;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public final class ObjectHelper {

  public static MockMvc createMockMvc(Object controller) { return standaloneSetup(controller).build(); }

  public static String mapToJSON(Object obj) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    mapper.registerModule(new JavaTimeModule());
    return mapper.writeValueAsString(obj);
  }

  public static User createDefaultUser() {
    return new User("Default User", LocalDate.now());
  }

  public static Video createDefaultVideo(User user) {
    return new Video("New video", "New subject", 20, COMEDY, user);
  }

  public static Playlist createDefaultPlaylistWithVideos(User user, List<Video> videos) {
    return Playlist.playlistWithVideos("New playlist", user, videos);
  }
}
