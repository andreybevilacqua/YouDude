package com.abevilacqua.youdude.utils;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public final class ObjectHelper {

  public static MockMvc createMockMvc(Object controller) { return standaloneSetup(controller).build(); }
}
