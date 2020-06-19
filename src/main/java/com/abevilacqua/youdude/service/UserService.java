package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.jpa.UserRepo;
import com.abevilacqua.youdude.repo.pageable.UserRepoPageable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.service.GenericService.getAll;
import static com.abevilacqua.youdude.service.GenericService.simulateSlowService;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@Slf4j
@Service
public class UserService {

  private final UserRepoPageable userRepoPageable;
  private final UserRepo userRepo;

  @Autowired
  public UserService(UserRepoPageable userRepoPageable,
                     UserRepo userRepo) {
    this.userRepoPageable = userRepoPageable;
    this.userRepo = userRepo;
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

  public Optional<User> deleteUser(final UUID id) {
    Optional<User> userOptional = userRepo.findById(id);
    userOptional.ifPresent(user -> {
      System.out.println("Thread running deleteUser service: " + Thread.currentThread());
      userRepo.deleteById(id);
    });
    return userOptional;
  }

}
