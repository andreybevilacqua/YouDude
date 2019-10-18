package com.abevilacqua.youdude.repo;

import com.abevilacqua.youdude.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepo extends JpaRepository<Video, Long> {
}
