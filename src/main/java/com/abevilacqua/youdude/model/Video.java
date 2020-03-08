package com.abevilacqua.youdude.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Getter
public final class Video {

  private int hashCode;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "video_id")
  private long id;

  @Column(name = "name")
  @NotEmpty
  @Setter
  private String name;

  @Column(name = "subject")
  @NotEmpty
  @Setter
  private String subject;

  @Column(name = "duration")
  @NotNull
  @Setter
  private int duration;

  @Column(name = "category")
  @Setter
  private Category category;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @Setter
  private User user;

  public Video(final String name,
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
    int result = hashCode;
    if(result == 0) {
      result = Long.hashCode(id);
      result = 31 * result + name.hashCode();
      result = 31 * result + Integer.hashCode(duration);
      result = 31 * result + category.hashCode();
      hashCode = result;
    }
    return result;
  }
}
