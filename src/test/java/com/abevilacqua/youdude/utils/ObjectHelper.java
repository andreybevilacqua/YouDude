package com.abevilacqua.youdude.utils;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

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
    return User.newInstance("Default User", LocalDate.now());
  }

  public static Video updateDefaultVideo(Video video) {
    return Video.newInstanceWithId(video.getId(), video.getName() + " default update",
        video.getSubject(),
        video.getDuration(),
        video.getCategory(),
        video.getUser());
  }

  public static Video createDefaultVideo(User user) {
    return Video.newInstance("New video", "New subject", 20, COMEDY, user);
  }

}
