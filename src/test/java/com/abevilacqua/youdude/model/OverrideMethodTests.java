package com.abevilacqua.youdude.model;

import org.junit.jupiter.api.Test;

import static com.abevilacqua.youdude.utils.ObjectHelper.createDefaultUser;
import static com.abevilacqua.youdude.utils.ObjectHelper.createDefaultVideo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OverrideMethodTests {

  private User user = createDefaultUser();
  private Video video = createDefaultVideo(user);

  @Test
  public void shouldGeneratePleasantToString() {
    User user = createDefaultUser();
    Video video = createDefaultVideo(user);

    System.out.println("Simulating a log message for user " + user);
    System.out.println("Simulating a log message for video " + video);
  }

  @Test
  public void shouldCalculateHashCode() {
    User anotherUser = createDefaultUser();
    Video anotherVideo = createDefaultVideo(anotherUser);

    assertEquals(user.hashCode(), anotherUser.hashCode());
    assertEquals(video.hashCode(), anotherVideo.hashCode());
  }
}
