package com.abevilacqua.youdude.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.InvalidParameterException;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@NoArgsConstructor
public final class Video implements Comparable<Video>{

  @Id
  @GeneratedValue(strategy = AUTO)
  @Column(name = "video_id")
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "subject")
  private String subject;

  @Column(name = "duration")
  private int duration;

  @Column(name = "category")
  @Enumerated(STRING)
  private Category category;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "playlist_id")
  private Playlist playlist;

  public static Video newInstance(final String name,
                                  final String subject,
                                  final int duration,
                                  final Category category,
                                  final User user) {
    requireNonNull(name, "Name should not be null");
    requireNonNull(subject, "Subject should not be null");
    requireNonNull(category, "Category should not be null");
    requireNonNull(user, "User should not be null");
    if(duration <= 0) throw new InvalidParameterException("Duration should be bigger than 0");
    return new Video(name, subject, duration, category, user);
  }

  private Video(final String name,
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

  @Override
  public String toString() {
    return "Id: " + id + ", Name: " + name + ", Category: " + category;
  }

  @Override
  public int hashCode() {
    int result;
    result = name.hashCode();
    result = 31 * result + Integer.hashCode(duration);
    result = 31 * result + category.hashCode();
    result = 31 * result + subject.hashCode();
    return result;
  }

  @Override
  public int compareTo(Video o) {
    return String.CASE_INSENSITIVE_ORDER.compare(name, o.getName());
  }
}
