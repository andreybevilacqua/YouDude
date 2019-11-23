package com.abevilacqua.youdude.controller.level_2;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/level2/users")
public class UserController {

  // todo: CompletableFuture, Paging, RESTFul, Cache, Spring Admin, integration tests

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers() {
    return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<User> getUserById(@PathVariable("user_id") long user_id) {
    return userService.getById(user_id)
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
  }

  @DeleteMapping("/{user_id}")
  public ResponseEntity deleteUser(@PathVariable("user_id") long user_id) {
    return userService.deleteUser(user_id)
        .map(user -> new ResponseEntity<>(HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
