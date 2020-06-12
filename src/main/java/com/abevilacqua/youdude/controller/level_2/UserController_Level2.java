package com.abevilacqua.youdude.controller.level_2;

import com.abevilacqua.youdude.controller.dto.PageImplDTO;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.controller.dto.PageImplDTO.pageMapper;

@RestController
@RequestMapping("/level2/users")
public class UserController_Level2 {

  // todo: Evo Suite, gRPC

  private final UserService userService;

  @Autowired
  public UserController_Level2(final UserService userService) {
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
  public ResponseEntity<User> getUserById(@PathVariable("user_id") final long user_id) {
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
  public ResponseEntity<User> deleteUser(@PathVariable("id") final long id) {
    if(id < 0) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    return userService.deleteUser(id)
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
