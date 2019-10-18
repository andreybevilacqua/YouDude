package com.abevilacqua.youdude.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private long id;

  @Column(name = "name")
  @NotEmpty
  private String name;

  @Column(name = "creation_date")
  @NotEmpty
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate creationDate;

  @OneToMany(mappedBy = "cart")
  private Set<Channel> channels;

  public User(String name, LocalDate creationDate) {
    this.name = name;
    this.creationDate = creationDate;
  }

  public User(String name, LocalDate creationDate, Set<Channel> channels) {
    this.name = name;
    this.creationDate = creationDate;
    this.channels = channels;
  }
}
