package com.abevilacqua.youdude.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
public class Channel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  private long id;

  @Column(name = "name")
  @NotEmpty
  private String name;

  @Column(name = "category")
  @NotEmpty
  private String category;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User owner;

  @OneToMany(mappedBy = "channel")
  private Set<Video> videos;
}
