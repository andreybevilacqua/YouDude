package com.abevilacqua.youdude.integration.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.jpa.PlaylistRepo;
import com.abevilacqua.youdude.repo.jpa.UserRepo;
import com.abevilacqua.youdude.repo.jpa.VideoRepo;
import com.abevilacqua.youdude.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.abevilacqua.youdude.utils.DBInitializer.initDB;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private VideoRepo videoRepo;

  @Autowired
  private PlaylistRepo playlistRepo;

  @Autowired
  private UserService service;

  @BeforeEach
  void setup() {
    if(userRepo.findAll().size() == 0) initDB(userRepo, videoRepo, playlistRepo);
  }

  @Test
  @DisplayName("Should update user")
  public void shouldUpdateUser() {
    Optional<User> first = userRepo.findAll().stream().findFirst();
    assertTrue(first.isPresent());

    String newName = "new name";
    int result = service.updateUserName(newName, first.get().getId());
    assertEquals(1, result);

    Optional<User> userUpdated = userRepo.findById(first.get().getId());
    assertTrue(userUpdated.isPresent());
    assertEquals("new name", userUpdated.get().getName());
  }
}
