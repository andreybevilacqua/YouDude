package com.abevilacqua.youdude;

import com.abevilacqua.youdude.repo.pageable.PlaylistRepoPageable;
import com.abevilacqua.youdude.repo.pageable.UserRepoPageable;
import com.abevilacqua.youdude.repo.pageable.VideoRepoPageable;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

import static com.abevilacqua.youdude.config.DBInitializer.initDB;

@SpringBootApplication
@EnableAsync
@EnableCaching
public class YouDudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(YouDudeApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(UserRepoPageable userRepoPageable,
									PlaylistRepoPageable playlistRepoPageable,
									VideoRepoPageable videoRepoPageable) {
		return initDB(userRepoPageable, playlistRepoPageable, videoRepoPageable);
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
