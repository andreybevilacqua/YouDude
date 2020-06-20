package com.abevilacqua.youdude.repo.jpa;

import com.abevilacqua.youdude.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

  @Query("SELECT u FROM user_youdude u ORDER BY u.name")
  List<User> findAll();

  @Query("SELECT u FROM user_youdude u WHERE u.id = ?1")
  Optional<User> findById(UUID id);

  @Query("DELETE FROM user_youdude u WHERE u.id = ?1")
  void deleteUserById(UUID id);
}
