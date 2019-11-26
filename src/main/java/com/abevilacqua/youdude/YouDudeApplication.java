package com.abevilacqua.youdude;

import com.abevilacqua.youdude.config.DBInitializer;
import com.abevilacqua.youdude.repo.PlaylistRepo;
import com.abevilacqua.youdude.repo.UserRepo;
import com.abevilacqua.youdude.repo.VideoRepo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class YouDudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouDudeApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(UserRepo userRepo,
									PlaylistRepo playlistRepo,
									VideoRepo videoRepo) {
		return DBInitializer.initDB(userRepo, playlistRepo, videoRepo);
	}

}
