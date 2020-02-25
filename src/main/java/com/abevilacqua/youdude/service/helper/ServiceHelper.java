package com.abevilacqua.youdude.service.helper;

import lombok.SneakyThrows;

public final class ServiceHelper {

  @SneakyThrows
  public static void simulateSlowService() {
    Thread.sleep(3000L);
  }
}
