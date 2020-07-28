package com.abevilacqua.youdude.repo.jpa;

import com.abevilacqua.youdude.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

  @Query("SELECT u FROM user_youdude u ORDER BY u.name")
  List<User> findAll();

  @Query("SELECT u FROM user_youdude u WHERE u.id = ?1")
  Optional<User> findById(UUID id);

  @Transactional
  @Modifying
  @Query("UPDATE user_youdude u SET u.name = ?1 WHERE u.id = ?2")
  int updateName(String name, UUID id);

  @Transactional
  @Modifying
  @Query("UPDATE user_youdude u SET u.creationDate = ?1 WHERE u.id = ?2")
  int updateCreationDate(LocalDate creationDate, UUID id);

  @Transactional
  @Modifying
  @Query("DELETE FROM user_youdude u WHERE u.id = ?1")
  void deleteUserById(UUID id);
}
