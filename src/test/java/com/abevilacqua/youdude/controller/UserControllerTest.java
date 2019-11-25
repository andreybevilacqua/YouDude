package com.abevilacqua.youdude.controller;

import com.abevilacqua.youdude.controller.level_2.UserController;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.service.UserService;
import com.abevilacqua.youdude.utils.ObjectHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static com.abevilacqua.youdude.utils.ObjectHelper.createDefaultUser;
import static com.abevilacqua.youdude.utils.ObjectHelper.mapToJSON;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserControllerTest {

  @Autowired
  private UserService userService;

  private MockMvc mockMvc;

  private final String URL = "/level2/users";

  @BeforeEach
  void setup() {
    UserController userController = new UserController(userService);
    mockMvc = ObjectHelper.createMockMvc(userController);
  }

  @Test
  @DisplayName("Should find all users")
  void shouldFindAllUsers() throws Exception {
    mockMvc.perform(get(URL)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").exists())
        .andExpect(jsonPath("$[0].id").isNumber())
        .andExpect(jsonPath("$[0].id", is(1)))
        .andExpect(jsonPath("$[1].id").exists())
        .andExpect(jsonPath("$[1].id").isNumber())
        .andExpect(jsonPath("$[1].id", is(2)))
        .andExpect(jsonPath("$[2].id").exists())
        .andExpect(jsonPath("$[2].id").isNumber())
        .andExpect(jsonPath("$[2].id", is(3)))
        .andExpect(jsonPath("$[0].name", is("user-1")))
        .andExpect(jsonPath("$[1].name", is("user-2")))
        .andExpect(jsonPath("$[2].name", is("user-3")));
  }

  @Test
  @DisplayName("Should find user by ID")
  void shouldFindUserById() throws Exception {
    String id = "/" + 1;
    mockMvc.perform(get(URL + id)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.id", is(1)))
        .andExpect(jsonPath("$.name").exists())
        .andExpect(jsonPath("$.name", is("user-1")))
        .andExpect(jsonPath("$.creationDate").exists())
        .andExpect(jsonPath("$.creationDate", is(LocalDate.now().toString())));
  }

  @Test
  @DisplayName("Should create a new user")
  void shouldCreateUser() throws Exception {
    User user = createDefaultUser();
    mockMvc.perform(post(URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapToJSON(user)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").exists())
        .andExpect(jsonPath("$.name").exists())
        .andExpect(jsonPath("$.name", is("Default User")))
        .andExpect(jsonPath("$.creationDate").exists())
        .andExpect(jsonPath("$.creationDate", is(LocalDate.now().toString())));
  }
}
