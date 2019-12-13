package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.UserRepo;
import com.abevilacqua.youdude.service.helper.ServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.abevilacqua.youdude.service.helper.ServiceHelper.simulateSlowService;
import static java.util.concurrent.CompletableFuture.completedFuture;

@Service
public class UserService {

  private UserRepo userRepo;

  @Autowired
  public UserService(UserRepo userRepo) { this.userRepo = userRepo; }

  @Async
  @Cacheable("getAllUsers")
  public CompletableFuture<Page<User>> getAllUsers(int page, int size, String sortBy) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return completedFuture(userRepo.findAll(pageable));
  }

  @Async
  @Cacheable("getById")
  public CompletableFuture<Optional<User>> getById(long id) {
    simulateSlowService();
    return completedFuture(userRepo.findById(id));
  }

  @Async
  public CompletableFuture<User> createUser(User user) {
    return completedFuture(userRepo.save(user));
  }

}
