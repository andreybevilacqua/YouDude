package com.abevilacqua.youdude.model;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Getter
public final class Video implements Comparable<Video>{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "video_id")
  private long id;

  @Column(name = "name")
  @NotEmpty
  private String name;

  @Column(name = "subject")
  @NotEmpty
  private String subject;

  @Column(name = "duration")
  @NotNull
  private int duration;

  @Column(name = "category")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  Video(){}

  Video(final String name,
        final String subject,
        final int duration,
        final Category category,
        final User user) {
    this.name = name;
    this.subject = subject;
    this.duration = duration;
    this.category = category;
    this.user = user;
  }

  Video(final long id,
        final String name,
        final String subject,
        final int duration,
        final Category category,
        final User user) {
    this.id = id;
    this.name = name;
    this.subject = subject;
    this.duration = duration;
    this.category = category;
    this.user = user;
  }

  public static Video newInstance(
      final String name,
      final String subject,
      final int duration,
      final Category category,
      final User user) {
    return new Video(name, subject, duration, category, user);
  }

  public static Video newInstanceWithId(
      final long id,
      final String name,
      final String subject,
      final int duration,
      final Category category,
      final User user) {
    return new Video(id, name, subject, duration, category, user);
  }

  @Override
  public String toString() {
    return "Id: " + id + ", Name: " + name + ", Category: " + category;
  }

  @Override
  public int hashCode() {
    int result;
    result = Long.hashCode(id);
    result = 31 * result + name.hashCode();
    result = 31 * result + Integer.hashCode(duration);
    result = 31 * result + category.hashCode();
    return result;
  }

  @Override
  public int compareTo(Video o) {
    return String.CASE_INSENSITIVE_ORDER.compare(name, o.getName());
  }
}
