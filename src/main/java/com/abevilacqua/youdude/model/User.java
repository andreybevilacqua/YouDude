package com.abevilacqua.youdude.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class User {

  private int hashCode; // Lazily initialized cached hash code.

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "creation_date")
  @JsonFormat(pattern = "yyyy-MM-dd")
  private LocalDate creationDate;

  public User(final String name,
              final LocalDate creationDate) {
    this.name = name;
    this.creationDate = creationDate;
  }

  @Override
  public String toString() {
    return "Id: " + id + ", Name: " + name + ", Creation Date: " + creationDate;
  }

  @Override
  public int hashCode() {
    int result = hashCode;
    if(result == 0) {
      result = Long.hashCode(id);
      result = 31 * result + name.hashCode();
      result = 31 * result + creationDate.hashCode();
      hashCode = result;
    }
    return result;
  }
}
