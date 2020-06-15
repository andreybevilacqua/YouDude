package com.abevilacqua.youdude.controller.hateoas;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.resource.UserResource;
import com.abevilacqua.youdude.model.resource.UserResourceAssembler;
import com.abevilacqua.youdude.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/hateoas/users", produces = "application/hal+json")
public class UserControllerHateoas {

  private final UserService userService;

  @Autowired
  public UserControllerHateoas(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<CollectionModel<UserResource>> getAllUsers() {
    CompletableFuture<List<User>> completableFuture = userService.getAllUsers();

    CollectionModel<UserResource> userResources =
        new UserResourceAssembler().toCollectionModel(completableFuture.join());

    userResources.add(WebMvcLinkBuilder
        .linkTo(methodOn(UserControllerHateoas.class).getAllUsers())
        .withRel("recents"));
    return new ResponseEntity<>(userResources, HttpStatus.OK);
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<UserResource> getUserById(@PathVariable("user_id") UUID user_id) {
    CompletableFuture<Optional<User>> completableFuture = userService.getById(user_id);
    return completableFuture
        .join()
        .map(user -> {
          UserResource userResource = new UserResourceAssembler().toModel(user);
          userResource
              .add(WebMvcLinkBuilder
              .linkTo(methodOn(UserControllerHateoas.class).getUserById(user_id))
              .withRel("base-uri"));
          return new ResponseEntity<>(userResource, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping(consumes = "application/json")
  public ResponseEntity<UserResource> createUser(@RequestBody User user) {
    CompletableFuture<User> completableFuture = userService.createUser(user);
    UserResource userResource = new UserResourceAssembler().toModel(completableFuture.join());
    return new ResponseEntity<>(userResource, HttpStatus.CREATED);
  }
}
