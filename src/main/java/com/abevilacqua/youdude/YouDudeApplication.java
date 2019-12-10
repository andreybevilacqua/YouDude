package com.abevilacqua.youdude;

import com.abevilacqua.youdude.config.DBInitializer;
import com.abevilacqua.youdude.repo.PlaylistRepo;
import com.abevilacqua.youdude.repo.UserRepo;
import com.abevilacqua.youdude.repo.VideoRepo;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
@EnableCaching
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

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		return executor;
	}

}
