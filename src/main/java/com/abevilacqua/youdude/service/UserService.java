package com.abevilacqua.youdude.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private UserRepo userRepo;

  @Autowired
  public UserService(UserRepo userRepo) { this.userRepo = userRepo; }

  public List<User> getAllUsers() {
    return userRepo.findAll();
  }

  public Optional<User> getById(long id) { return userRepo.findById(id);}

  public User createUser(User user) { return userRepo.save(user); }

  public Optional<User> deleteUser(long userId) {
    Optional<User> optionalUser = userRepo
        .findById(userId);
    if(optionalUser.isPresent()) {
      userRepo.delete(optionalUser.get());
      return optionalUser;
    } else return Optional.empty();
  }
}
