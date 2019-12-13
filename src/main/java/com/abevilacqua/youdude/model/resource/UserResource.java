package com.abevilacqua.youdude.model.resource;

import com.abevilacqua.youdude.model.User;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Getter
public class UserResource extends RepresentationModel<UserResource> {

  private String name;

  private LocalDate creationDate;

  // I decide to remove the User ID from the Resource, because it's a internal db info
  // and doesn't have to be exposed in the API. The link itself will give the correct data.
  public UserResource(User user) {
    this.name = user.getName();
    this.creationDate = user.getCreationDate();
  }

}
