package com.abevilacqua.youdude.controller.rest;

import com.abevilacqua.youdude.controller.dto.PageImplDTO;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.service.UserService;
import com.abevilacqua.youdude.service.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.controller.dto.PageImplDTO.pageMapper;

@RestController
@RequestMapping("/rest/users")
public class UserControllerRest {

  // todo: YouDude-Auth into YouDude-Admin, Evo Suite, gRPC, pitest

  private final UserService userService;
  private final SecurityService securityService;

  @Autowired
  public UserControllerRest(final UserService userService,
                            final SecurityService securityService) {
    this.userService = userService;
    this.securityService = securityService;
  }

  @GetMapping
  public ResponseEntity<PageImplDTO<User>> getAllUsers(
      @RequestParam(value = "page", defaultValue = "0") final int page,
      @RequestParam(value = "size", defaultValue = "10") final int size,
      @RequestParam(value = "sort", defaultValue = "id") final String sortBy,
      @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    CompletableFuture<Page<User>> completableFuture = userService.getAllUsers(page, size, sortBy);
    Page<User> userPage = completableFuture.join();
    return new ResponseEntity<PageImplDTO<User>>(pageMapper(userPage), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") final UUID id,
                                          @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    CompletableFuture<Optional<User>> completableFuture = userService.getById(id);
    return completableFuture
        .join()
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody final User user,
                                         @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    CompletableFuture<User> completableFuture = userService.createUser(user);
    return new ResponseEntity<>(completableFuture.join(), HttpStatus.CREATED);
  }

  @PutMapping("/name")
  public ResponseEntity<User> updateUserName(@RequestParam(name = "name") final String name,
                                             @RequestParam(name = "id") final UUID id,
                                             @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    CompletableFuture<User> completableFuture = userService.updateUserName(name, id);
    return new ResponseEntity<>(completableFuture.join(), HttpStatus.OK);
  }

  @PutMapping("/creation-date")
  public ResponseEntity<User> updateUserCreationDate(@RequestParam(name = "creation-date") final String creationDate,
                                                     @RequestParam(name = "id") final UUID id,
                                                     @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    LocalDate parsedDate = LocalDate.parse(creationDate);
    CompletableFuture<User> completableFuture = userService.updateUserCreationDate(parsedDate, id);
    return new ResponseEntity<>(completableFuture.join(), HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable("id") final UUID id,
                                         @RequestHeader("token") String token) {
    securityService.processClientRequest(token);
    if(id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    return userService.deleteUser(id)
        .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

}
