package com.abevilacqua.youdude.repo.jpa;

import com.abevilacqua.youdude.model.Category;
import com.abevilacqua.youdude.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VideoRepo extends JpaRepository<Video, UUID> {

  @Query("SELECT v FROM Video v WHERE v.user = ?1")
  List<Video> findAllByUser(UUID userId);

  List<Video> findAllByCategory(final Category category);
}
