package com.abevilacqua.youdude.unit.service;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.jpa.PlaylistRepo;
import com.abevilacqua.youdude.repo.jpa.UserRepo;
import com.abevilacqua.youdude.repo.jpa.VideoRepo;
import com.abevilacqua.youdude.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static com.abevilacqua.youdude.utils.ObjectHelper.createDefaultUser;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTest {

  @MockBean
  private UserRepo userRepo;

  @MockBean
  @SuppressWarnings("unused")
  private VideoRepo videoRepo;

  @MockBean
  @SuppressWarnings("unused")
  private PlaylistRepo playlistRepo;

  @Autowired
  private UserService userService;

  @Test
  @DisplayName("Should get all users")
  public void shouldGetAllUsers() throws ExecutionException, InterruptedException {
    when(userRepo.findAll()).thenReturn(singletonList(createDefaultUser()));
    List<User> users = userService.getAllUsers().get();
    assertEquals(1, users.size());
    assertEquals("Default User", users.get(0).getName());
    assertEquals(LocalDate.now(), users.get(0).getCreationDate());
  }

  @Test
  @DisplayName("Should get user by id")
  public void shouldGetUserById() throws ExecutionException, InterruptedException {
    UUID random = UUID.randomUUID();
    when(userRepo.findById(random)).thenReturn(Optional.of(createDefaultUser()));
    Optional<User> optionalUser = userService.getById(random).get();
    assertTrue(optionalUser.isPresent());
    assertNull(optionalUser.get().getId());
    assertEquals("Default User", optionalUser.get().getName());
    assertEquals(LocalDate.now(), optionalUser.get().getCreationDate());
  }

  @Test
  @DisplayName("Should create user")
  public void shouldCreateUser() throws ExecutionException, InterruptedException {
    User newUser = createDefaultUser();
    when(userRepo.save(newUser)).thenReturn(newUser);
    User user = userService.createUser(newUser).get();
    assertNull(user.getId());
    assertEquals("Default User", user.getName());
    assertEquals(LocalDate.now(), user.getCreationDate());
  }


}
