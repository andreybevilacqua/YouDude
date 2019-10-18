package com.abevilacqua.youdude.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
public class Video {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
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

  @ManyToOne
  @JoinColumn(name = "channel_id")
  private Channel channel;

  public Video(String name, String subject, int duration) {
    this.name = name;
    this.subject = subject;
    this.duration = duration;
  }
}
