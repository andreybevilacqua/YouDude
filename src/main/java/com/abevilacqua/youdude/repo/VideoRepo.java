package com.abevilacqua.youdude.repo;

import com.abevilacqua.youdude.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepo extends JpaRepository<Video, Long> {
}
