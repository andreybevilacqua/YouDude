package com.abevilacqua.youdude.repo.jpa;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepo extends JpaRepository<Video, Long> {

  List<Video> findAllByUser(final User user);
}
