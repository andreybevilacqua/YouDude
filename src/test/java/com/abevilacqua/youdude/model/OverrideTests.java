package com.abevilacqua.youdude.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.abevilacqua.youdude.utils.ObjectHelper.createDefaultUser;
import static com.abevilacqua.youdude.utils.ObjectHelper.createDefaultVideo;

public class OverrideTests {

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
    System.out.println("HashCode for user: " + user.hashCode());
    System.out.println("HashCode for video: " + video.hashCode());

    User anotherUser = createDefaultUser();
    System.out.println("First hashcode: " + user.hashCode());
    System.out.println("Second hashcode: " + anotherUser.hashCode());

    Assertions.assertEquals(user.hashCode(), anotherUser.hashCode());
  }
}
