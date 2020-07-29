package com.abevilacqua.yoududeadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class YouDudeAdmin {

  public static void main(String[] args) {
    SpringApplication.run(YouDudeAdmin.class, args);
  }

}
