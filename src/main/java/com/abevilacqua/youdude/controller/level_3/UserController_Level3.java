package com.abevilacqua.youdude.controller.level_3;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.resource.UserResource;
import com.abevilacqua.youdude.model.resource.UserResourceAssembler;
import com.abevilacqua.youdude.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/level3/users")
public class UserController_Level3 {

  private UserService userService;

  @Autowired
  public UserController_Level3(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<CollectionModel<UserResource>> getAllUsers() {
    CompletableFuture<List<User>> completableFuture = userService.getAllUsers();

    CollectionModel<UserResource> userResources =
        new UserResourceAssembler().toCollectionModel(completableFuture.join());

    userResources.add(WebMvcLinkBuilder
        .linkTo(methodOn(UserController_Level3.class).getAllUsers())
        .withRel("recents"));
    return new ResponseEntity<>(userResources, HttpStatus.OK);
  }

}
