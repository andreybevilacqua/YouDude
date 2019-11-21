package com.abevilacqua.youdude.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "creation_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate creationDate;

  @OneToMany(mappedBy = "user")
  private List<Video> videos;

  public User(String name, LocalDate creationDate) {
    this.name = name;
    this.creationDate = creationDate;
  }

  public User(String name, LocalDate creationDate, List<Video> videos) {
    this.name = name;
    this.creationDate = creationDate;
    this.videos = videos;
  }
}
