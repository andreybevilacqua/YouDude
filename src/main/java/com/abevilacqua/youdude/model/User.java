package com.abevilacqua.youdude.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

import static java.util.Objects.requireNonNull;
import static javax.persistence.GenerationType.AUTO;

@Entity(name = "user_youdude")
@Getter
@Builder
@NoArgsConstructor
public final class User implements Comparable<User>{

  @Id
  @GeneratedValue(strategy = AUTO)
  @Column(name = "user_id")
  private UUID id;

  @Column(name = "name")
  private String name;

  @Column(name = "creation_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate creationDate;

  public static User newInstance(final String name, final LocalDate creationDate) {
    requireNonNull(name, "Name should not be null");
    requireNonNull(creationDate, "Creation date should not be null");
    return new User(name, creationDate);
  }

  public static User newInstance(final UUID id, final String name, final LocalDate creationDate) {
    requireNonNull(id, "ID should not be null");
    requireNonNull(name, "Name should not be null");
    requireNonNull(creationDate, "Creation date should not be null");
    return new User(id, name, creationDate);
  }

  private User(String name, final LocalDate creationDate) {
    this.name = name;
    this.creationDate = creationDate;
  }

  private User(final UUID id, String name, final LocalDate creationDate) {
    this.id = id;
    this.name = name;
    this.creationDate = creationDate;
  }

  @Override
  public String toString() {
    return "Id: " + id + ", Name: " + name + ", Creation Date: " + creationDate;
  }

  @Override
  public int hashCode() {
    int result;
    result = name.hashCode();
    result = 31 * result + creationDate.hashCode();
    return result;
  }

  @Override
  public int compareTo(User o) {
    return String.CASE_INSENSITIVE_ORDER.compare(name, o.getName());
  }
}
