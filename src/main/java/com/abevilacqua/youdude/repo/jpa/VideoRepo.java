package com.abevilacqua.youdude.repo.jpa;

import com.abevilacqua.youdude.model.Category;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface VideoRepo extends JpaRepository<Video, UUID> {

  @Query("SELECT v FROM Video v ORDER BY v.name")
  List<Video> findAll();

  @Query("SELECT v FROM Video v WHERE v.user = ?1")
  List<Video> findAllByUser(UUID userId);

  List<Video> findAllByCategory(final Category category);

  @Transactional
  @Modifying
  @Query("DELETE FROM Video v WHERE v.user = ?1")
  void deleteAllByUser(final User user);
}
