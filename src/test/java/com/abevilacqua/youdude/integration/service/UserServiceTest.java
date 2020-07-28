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

import java.time.LocalDate;
import java.util.Optional;

import static com.abevilacqua.youdude.utils.DBInitializer.initDB;
import static org.junit.jupiter.api.Assertions.*;

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
  @DisplayName("Should update user name")
  public void shouldUpdateUserName() {
    Optional<User> first = userRepo.findAll().stream().findFirst();
    assertTrue(first.isPresent());

    String newName = "new name";
    User result = service.updateUserName(newName, first.get().getId()).join();
    assertNotNull(result);
    assertEquals("new name", result.getName());
    assertEquals(first.get().getCreationDate(), result.getCreationDate());
  }

  @Test
  @DisplayName("Should update user creation date")
  public void shouldUpdateUserCreationDate() {
    Optional<User> first = userRepo.findAll().stream().findFirst();
    assertTrue(first.isPresent());

    LocalDate creationDate = LocalDate.of(2001, 1, 1);
    User result = service.updateUserCreationDate(creationDate, first.get().getId()).join();
    assertNotNull(result);
    assertEquals(creationDate, result.getCreationDate());
    assertEquals(first.get().getName(), result.getName());
  }
}
