package com.abevilacqua.youdude.repo;

import com.abevilacqua.youdude.model.Category;
import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepo extends JpaRepository<Video, Long> {

  List<Video> findAllByUser(User user);
  List<Video> findAllByCategory(Category category);
}
