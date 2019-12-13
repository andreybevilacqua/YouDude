package com.abevilacqua.youdude.controller.level_3;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.core.ControllerEntityLinksFactoryBean;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;
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
  public ResponseEntity<CollectionModel<EntityModel<User>>> getAllUsers(
      @RequestParam(value = "page", defaultValue = "0") final int page,
      @RequestParam(value = "size", defaultValue = "10") final int size,
      @RequestParam(value = "sort", defaultValue = "id") final String sortBy) {
    CompletableFuture<Page<User>> completableFuture = userService.getAllUsers(page, size, sortBy);
    Page<User> userPage = completableFuture.join();
    CollectionModel<EntityModel<User>> recentResources = CollectionModel.wrap(userPage.getContent());
    recentResources.add(
        WebMvcLinkBuilder
            .linkTo(methodOn(UserController_Level3.class).getAllUsers(page, size, sortBy))
            .withSelfRel());
    return new ResponseEntity<>(recentResources, HttpStatus.OK);
  }

}
