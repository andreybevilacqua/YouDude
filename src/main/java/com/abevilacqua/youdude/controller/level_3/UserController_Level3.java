package com.abevilacqua.youdude.controller.level_3;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.controller.level_3.link.LinkHelper.getUserPath;

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
    recentResources.add(new Link(getUserPath(), "recent"));
    return new ResponseEntity<>(recentResources, HttpStatus.OK);
  }

}
