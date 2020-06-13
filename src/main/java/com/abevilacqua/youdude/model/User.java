package com.abevilacqua.youdude.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "user_application")
@Getter
@AllArgsConstructor
public final class User implements Comparable<User>{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "creation_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate creationDate;

  User(){}

  User(final String name, final LocalDate creationDate) {
    this.name = name;
    this.creationDate = creationDate;
  }

  public static User newInstance(final String name, final LocalDate creationDate) {
    return new User(name, creationDate);
  }

  @Override
  public String toString() {
    return "Id: " + id + ", Name: " + name + ", Creation Date: " + creationDate;
  }

  @Override
  public int hashCode() {
    int result;
    result = id.toString().hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + creationDate.hashCode();
    return result;
  }

  @Override
  public int compareTo(User o) {
    return String.CASE_INSENSITIVE_ORDER.compare(name, o.getName());
  }
}
