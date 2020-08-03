package com.abevilacqua.youdude.integration.controller.rest;

import com.abevilacqua.youdude.controller.rest.UserControllerRest;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.repo.jpa.PlaylistRepo;
import com.abevilacqua.youdude.repo.jpa.UserRepo;
import com.abevilacqua.youdude.repo.jpa.VideoRepo;
import com.abevilacqua.youdude.service.UserService;
import com.abevilacqua.youdude.service.security.SecurityService;
import com.abevilacqua.youdude.utils.ObjectHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerRestTest {

  @Autowired
  private UserService userService;

  @MockBean
  private SecurityService securityService;

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
    UserControllerRest userControllerLevel2 = new UserControllerRest(userService, securityService);
    mockMvc = ObjectHelper.createMockMvc(userControllerLevel2);
    if(userRepo.findAll().size() == 0) initDB(userRepo, videoRepo, playlistRepo);
    doNothing().when(securityService).processClientRequest(anyString());
  }

  @Test
  @DisplayName("Should find all users")
  void shouldFindAllUsers() throws Exception {
    mockMvc.perform(get(URL)
        .contentType(APPLICATION_JSON)
        .header("token", "some-valid-token"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.[0].id").exists())
        .andExpect(jsonPath("$.content.[0].id").isString())
        .andExpect(jsonPath("$.content.[1].id").exists())
        .andExpect(jsonPath("$.content.[1].id").isString())
        .andExpect(jsonPath("$.content.[2].id").exists())
        .andExpect(jsonPath("$.content.[2].id").isString())
        .andExpect(jsonPath("$.content.[0].name").isString())
        .andExpect(jsonPath("$.content.[1].name").isString())
        .andExpect(jsonPath("$.content.[2].name").isString());
  }

  @Test
  @DisplayName("Should find user by ID")
  void shouldFindUserById() throws Exception {
    Optional<User> first = userRepo.findAll().stream().findFirst();
    assertTrue(first.isPresent());
    User user = first.get();

    mockMvc.perform(get(URL + "/" + first.get().getId())
        .contentType(APPLICATION_JSON)
        .header("token", "some-valid-token"))
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
        .content(mapToJSON(user))
        .header("token", "some-valid-token"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name", is("Default User")))
        .andExpect(jsonPath("$.creationDate", is(LocalDate.now().toString())));
  }

  @Test
  @DisplayName("Should update user name")
  void shouldUpdateUserName() throws Exception {
    Optional<User> user = userRepo.findAll().stream().findFirst();
    assertTrue(user.isPresent());
    String name = "new name";
    mockMvc.perform(put(URL + "/name")
        .contentType(APPLICATION_JSON)
        .param("name", name)
        .param("id", user.get().getId().toString())
        .header("token", "some-valid-token"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name", is("new name")))
        .andExpect(jsonPath("$.creationDate", is(user.get().getCreationDate().toString())));
  }

  @Test
  @DisplayName("Should update user creation date")
  void shouldUpdateUserCreationDate() throws Exception {
    Optional<User> user = userRepo.findAll().stream().findFirst();
    assertTrue(user.isPresent());
    LocalDate creationDate = LocalDate.of(2001, 1, 1);
    mockMvc.perform(put(URL + "/creation-date")
        .contentType(APPLICATION_JSON)
        .param("creation-date", creationDate.toString())
        .param("id", user.get().getId().toString())
        .header("token", "some-valid-token"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name", is(user.get().getName())))
        .andExpect(jsonPath("$.creationDate", is(creationDate.toString())));
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
          .contentType(APPLICATION_JSON)
          .header("token", "some-valid-token"))
          .andExpect(status().isOk());
    }
  }

  @Test
  @DisplayName("Should fail during user delete process")
  void shouldFailDuringUserDeleteProcess() throws Exception {
    mockMvc.perform(delete(URL + "/" + randomUUID().toString())
        .contentType(APPLICATION_JSON)
        .header("token", "some-valid-token"))
        .andExpect(status().isNotFound());
  }
}
