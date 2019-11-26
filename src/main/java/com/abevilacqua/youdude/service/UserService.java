package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

@Service
public class UserService {

  private UserRepo userRepo;

  @Autowired
  public UserService(UserRepo userRepo) { this.userRepo = userRepo; }

  @Async
  public CompletableFuture<List<User>> getAllUsers() {
    return completedFuture(userRepo.findAll());
  }

  @Async
  public CompletableFuture<Optional<User>> getById(long id) {
    return completedFuture(userRepo.findById(id));
  }

  @Async
  public CompletableFuture<User> createUser(User user) {
    return completedFuture(userRepo.save(user));
  }

}
