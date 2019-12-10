package com.abevilacqua.youdude.service.helper;

public final class ServiceHelper {

  public static void simulateSlowService() {
    try {
      Thread.sleep(3000L);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
