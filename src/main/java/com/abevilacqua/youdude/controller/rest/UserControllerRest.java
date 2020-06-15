package com.abevilacqua.youdude.controller.rest;

import com.abevilacqua.youdude.controller.dto.PageImplDTO;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.controller.dto.PageImplDTO.pageMapper;

@RestController
@RequestMapping("/rest/users")
public class UserControllerRest {

  // todo: Evo Suite, gRPC

  private final UserService userService;

  @Autowired
  public UserControllerRest(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<PageImplDTO<User>> getAllUsers(
      @RequestParam(value = "page", defaultValue = "0") final int page,
      @RequestParam(value = "size", defaultValue = "10") final int size,
      @RequestParam(value = "sort", defaultValue = "id") final String sortBy) {
    CompletableFuture<Page<User>> completableFuture = userService.getAllUsers(page, size, sortBy);
    Page<User> userPage = completableFuture.join();
    return new ResponseEntity<PageImplDTO<User>>(pageMapper(userPage), HttpStatus.OK);
  }

  @GetMapping("/{user_id}")
  public ResponseEntity<User> getUserById(@PathVariable("user_id") final UUID user_id) {
    CompletableFuture<Optional<User>> completableFuture = userService.getById(user_id);
    return completableFuture
        .join()
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody final User user) {
    CompletableFuture<User> completableFuture = userService.createUser(user);
    return new ResponseEntity<>(completableFuture.join(), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable("id") final UUID id) {
    if(id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    return userService.deleteUser(id)
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}