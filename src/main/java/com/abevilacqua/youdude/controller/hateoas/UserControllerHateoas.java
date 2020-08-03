package com.abevilacqua.youdude.controller.hateoas;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.resource.UserResource;
import com.abevilacqua.youdude.model.resource.UserResourceAssembler;
import com.abevilacqua.youdude.service.UserService;
import com.abevilacqua.youdude.service.security.SecurityService;
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
  private final SecurityService securityService;

  @Autowired
  public UserControllerHateoas(final UserService userService,
                               final SecurityService securityService) {
    this.userService = userService;
    this.securityService = securityService;
  }

  @GetMapping
  public ResponseEntity<CollectionModel<UserResource>> getAllUsers(@RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    CompletableFuture<List<User>> completableFuture = userService.getAllUsers();

    CollectionModel<UserResource> userResources =
        new UserResourceAssembler().toCollectionModel(completableFuture.join());

    userResources.add(WebMvcLinkBuilder
        .linkTo(methodOn(UserControllerHateoas.class).getAllUsers(token))
        .withRel("recents"));
    return new ResponseEntity<>(userResources, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResource> getUserById(@PathVariable("id") UUID id,
                                                  @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    CompletableFuture<Optional<User>> completableFuture = userService.getById(id);
    return completableFuture
        .join()
        .map(user -> {
          UserResource userResource = new UserResourceAssembler().toModel(user);
          userResource
              .add(WebMvcLinkBuilder
              .linkTo(methodOn(UserControllerHateoas.class).getUserById(id, token))
              .withRel("base-uri"));
          return new ResponseEntity<>(userResource, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping(consumes = "application/json")
  public ResponseEntity<UserResource> createUser(@RequestBody User user,
                                                 @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    CompletableFuture<User> completableFuture = userService.createUser(user);
    UserResource userResource = new UserResourceAssembler().toModel(completableFuture.join());
    return new ResponseEntity<>(userResource, HttpStatus.CREATED);
  }
}
