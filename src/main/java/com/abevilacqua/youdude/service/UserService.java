package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.jpa.UserRepo;
import com.abevilacqua.youdude.repo.pageable.UserRepoPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.service.helper.ServiceHelper.simulateSlowService;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@Service
public class UserService {

  private UserRepoPageable userRepoPageable;
  private UserRepo userRepo;

  @Autowired
  public UserService(final UserRepoPageable userRepoPageable,
                     final UserRepo userRepo) {
    this.userRepoPageable = userRepoPageable;
    this.userRepo = userRepo;
  }

  @Async
  @Cacheable("getAllUsersPageable")
  public CompletableFuture<Page<User>> getAllUsers(final int page,
                                                   final int size,
                                                   final String sortBy) {
    System.out.println("Thread running getAllUsers pageable service: " + Thread.currentThread());
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return userRepoPageable.findAll(pageable);
    });
  }

  @Async
  @Cacheable("getAllUsers")
  public CompletableFuture<List<User>> getAllUsers() {
    System.out.println("Thread running getAllUsers service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return userRepo.findAll();
    });
  }

  @Async
  @Cacheable("getById")
  public CompletableFuture<Optional<User>> getById(final long id) {
    System.out.println("Thread running getAllUsers pageable service: " + Thread.currentThread());
    simulateSlowService();
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return userRepo.findById(id);
    });
  }

  @Async
  public CompletableFuture<User> createUser(final User user) {
    System.out.println("Thread running createUser service: " + Thread.currentThread());
    return supplyAsync(() -> {
      System.out.println("Thread running inside of supplyAsync: " + Thread.currentThread());
      return userRepoPageable.save(user);
    });
  }

  public Optional<User> deleteUser(final long id) {
    Optional<User> userOptional = userRepo.findById(id);
    userOptional.ifPresent(user -> {
      System.out.println("Thread running deleteUser service: " + Thread.currentThread());
      userRepo.deleteById(id);
    });
    return userOptional;
  }

}
