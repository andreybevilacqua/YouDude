package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.jpa.PlaylistRepo;
import com.abevilacqua.youdude.repo.jpa.UserRepo;
import com.abevilacqua.youdude.repo.jpa.VideoRepo;
import com.abevilacqua.youdude.repo.pageable.UserRepoPageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import static com.abevilacqua.youdude.model.User.newInstance;
import static com.abevilacqua.youdude.service.GenericService.getAll;
import static com.abevilacqua.youdude.service.GenericService.simulateSlowService;
import static java.lang.String.valueOf;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@Slf4j
@Service
public class UserService {

  private final UserRepoPageable userRepoPageable;
  private final UserRepo userRepo;
  private final VideoRepo videoRepo;
  private final PlaylistRepo playlistRepo;

  @Autowired
  public UserService(UserRepoPageable userRepoPageable,
                     UserRepo userRepo,
                     VideoRepo videoRepo,
                     PlaylistRepo playlistRepo) {
    this.userRepoPageable = userRepoPageable;
    this.userRepo = userRepo;
    this.videoRepo = videoRepo;
    this.playlistRepo = playlistRepo;
  }

  @Cacheable("getAllUsersPageable")
  public CompletableFuture<Page<User>> getAllUsers(final int page,
                                                   final int size,
                                                   final String sortBy) {
    System.out.println("Thread running getAllUsers pageable service: " + Thread.currentThread());
    return getAll(userRepoPageable, page, size, sortBy);
  }

  @Cacheable("getAllUsers")
  public CompletableFuture<List<User>> getAllUsers() {
    System.out.println("Thread running getAllUsers service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return userRepo.findAll();
    });
  }

  @Cacheable("getById")
  public CompletableFuture<Optional<User>> getById(final UUID id) {
    System.out.println("Thread running getAllUsers pageable service: " + Thread.currentThread());
    simulateSlowService();
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return userRepo.findById(id);
    });
  }

  public CompletableFuture<User> createUser(final User user) {
    System.out.println("Thread running createUser service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return userRepo.save(user);
    });
  }

  public CompletableFuture<User> updateUser(final User newUser, Map<String, Object> fieldsToChange) {
    System.out.println("Thread running updateUser service: " + Thread.currentThread());

    return supplyAsync(() ->
        userRepo
            .findById(newUser.getId())
            .map(user -> {
              User updatedUser = userWithFieldsToChange(fieldsToChange, user);
              userRepo.save(updatedUser);
              return updatedUser;
            })
            .orElseThrow(() -> new InvalidParameterException("User with ID does not exist")));
  }

  public Optional<User> deleteUser(final UUID id) {
    Optional<User> userOptional = userRepo.findById(id);
    userOptional.ifPresent(user -> {
      System.out.println("Thread running deleteUser service: " + Thread.currentThread());
      playlistRepo.deleteAllByUser(user);
      videoRepo.deleteAllByUser(user);
      userRepo.deleteUserById(id);
    });
    return userOptional;
  }

  private User userWithFieldsToChange(Map<String, Object> fieldsToChange, User oldUser) {
    AtomicReference<String> name = new AtomicReference<>(oldUser.getName());
    AtomicReference<LocalDate> creationDate = new AtomicReference<>(oldUser.getCreationDate());

    fieldsToChange
        .keySet()
        .forEach(field -> {
          if(field.equals("name")) name.set(valueOf(fieldsToChange.get(field)));
          if(field.equals("creationDate")) creationDate.set((LocalDate) fieldsToChange.get(field));
        });

    return newInstance(oldUser.getId(), name.get(), creationDate.get());
  }

}
