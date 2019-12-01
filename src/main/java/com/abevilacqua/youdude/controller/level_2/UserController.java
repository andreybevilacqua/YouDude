package com.abevilacqua.youdude.controller.level_2;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/level2/users")
public class UserController {

  // todo: Paging, Sort, RESTFul, Cache, Spring Admin

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    CompletableFuture<List<User>> completableFuture = userService.getAllUsers();
    return new ResponseEntity<>(completableFuture.join(), HttpStatus.OK);
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<User> getUserById(@PathVariable("user_id") long user_id) {
    CompletableFuture<Optional<User>> completableFuture = userService.getById(user_id);
    return completableFuture
        .join()
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    CompletableFuture<User> completableFuture = userService.createUser(user);
    return new ResponseEntity<>(completableFuture.join(), HttpStatus.CREATED);
  }

}
