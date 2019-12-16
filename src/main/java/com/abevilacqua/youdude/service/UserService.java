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
import static java.util.concurrent.CompletableFuture.completedFuture;

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
    simulateSlowService();
    Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
    return completedFuture(userRepoPageable.findAll(pageable));
  }

  @Async
  @Cacheable("getAllUsers")
  public CompletableFuture<List<User>> getAllUsers() {
    return completedFuture(userRepo.findAll());
  }

  @Async
  @Cacheable("getById")
  public CompletableFuture<Optional<User>> getById(final long id) {
    simulateSlowService();
    return completedFuture(userRepoPageable.findById(id));
  }

  @Async
  public CompletableFuture<User> createUser(final User user) {
    return completedFuture(userRepoPageable.save(user));
  }

}
