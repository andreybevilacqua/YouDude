package com.abevilacqua.youdude.integration.controller.rest;

import com.abevilacqua.youdude.controller.rest.UserControllerRest;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.jpa.PlaylistRepo;
import com.abevilacqua.youdude.repo.jpa.UserRepo;
import com.abevilacqua.youdude.repo.jpa.VideoRepo;
import com.abevilacqua.youdude.service.UserService;
import com.abevilacqua.youdude.utils.ObjectHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static com.abevilacqua.youdude.utils.DBInitializer.initDB;
import static com.abevilacqua.youdude.utils.ObjectHelper.createDefaultUser;
import static com.abevilacqua.youdude.utils.ObjectHelper.mapToJSON;
import static java.util.UUID.randomUUID;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerRestTest {

  @Autowired
  private UserService userService;

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private VideoRepo videoRepo;

  @Autowired
  private PlaylistRepo playlistRepo;

  private MockMvc mockMvc;

  private final String  URL = "/rest/users";

  @BeforeEach
  void setup() {
    UserControllerRest userControllerLevel2 = new UserControllerRest(userService);
    mockMvc = ObjectHelper.createMockMvc(userControllerLevel2);
    if(userRepo.findAll().size() == 0) initDB(userRepo, videoRepo, playlistRepo);
  }

  @Test
  @DisplayName("Should find all users")
  void shouldFindAllUsers() throws Exception {
    mockMvc.perform(get(URL)
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.[0].id").exists())
        .andExpect(jsonPath("$.content.[0].id").isString())
        .andExpect(jsonPath("$.content.[1].id").exists())
        .andExpect(jsonPath("$.content.[1].id").isString())
        .andExpect(jsonPath("$.content.[2].id").exists())
        .andExpect(jsonPath("$.content.[2].id").isString())
        .andExpect(jsonPath("$.content.[0].name", is("user-1")))
        .andExpect(jsonPath("$.content.[1].name", is("user-2")))
        .andExpect(jsonPath("$.content.[2].name", is("user-3")));
  }

  @Test
  @DisplayName("Should find user by ID")
  void shouldFindUserById() throws Exception {
    Optional<User> first = userRepo.findAll().stream().findFirst();
    assertTrue(first.isPresent());
    User user = first.get();

    mockMvc.perform(get(URL + "/" + first.get().getId())
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(user.getId().toString())))
        .andExpect(jsonPath("$.name", is(user.getName())))
        .andExpect(jsonPath("$.creationDate", is(user.getCreationDate().toString())));
  }

  @Test
  @DisplayName("Should create a new user")
  void shouldCreateUser() throws Exception {
    User user = createDefaultUser();
    mockMvc.perform(post(URL)
        .contentType(APPLICATION_JSON)
        .content(mapToJSON(user)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name", is("Default User")))
        .andExpect(jsonPath("$.creationDate", is(LocalDate.now().toString())));
  }

  @Test
  @DisplayName("Should delete user")
  void shouldDeleteUser() throws Exception {
    Optional<User> user = userService
        .getAllUsers()
        .join()
        .stream()
        .findFirst();
    if(user.isPresent()) {
      mockMvc.perform(delete(URL+ "/" + user.get().getId())
          .contentType(APPLICATION_JSON))
          .andDo(print())
          .andExpect(status().isOk());
    }
  }

  @Test
  @DisplayName("Should fail during user delete process")
  void shouldFailDuringUserDeleteProcess() throws Exception {
    mockMvc.perform(delete(URL + "/" + randomUUID().toString())
        .contentType(APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }
}
