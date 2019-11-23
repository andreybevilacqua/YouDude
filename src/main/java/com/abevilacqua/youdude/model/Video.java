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
public class Video {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

  public Video(String name, String subject, int duration, Category category, User user) {
    this.name = name;
    this.subject = subject;
    this.duration = duration;
    this.category = category;
    this.user = user;
  }
}
