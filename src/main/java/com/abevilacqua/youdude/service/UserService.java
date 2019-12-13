package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.UserRepoPageable;
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

  private UserRepoPageable userRepoPageable;

  @Autowired
  public UserService(UserRepoPageable userRepoPageable) { this.userRepoPageable = userRepoPageable; }

  @Async
  @Cacheable("getAllUsersPageable")
  public CompletableFuture<Page<User>> getAllUsers(int page, int size, String sortBy) {
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return completedFuture(userRepoPageable.findAll(pageable));
  }

  @Async
  @Cacheable("getById")
  public CompletableFuture<Optional<User>> getById(long id) {
    simulateSlowService();
    return completedFuture(userRepoPageable.findById(id));
  }

  @Async
  public CompletableFuture<User> createUser(User user) {
    return completedFuture(userRepoPageable.save(user));
  }

}
