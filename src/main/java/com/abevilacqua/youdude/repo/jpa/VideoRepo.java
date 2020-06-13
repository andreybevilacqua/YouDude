package com.abevilacqua.youdude.repo.jpa;

import com.abevilacqua.youdude.model.User;
import com.abevilacqua.youdude.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VideoRepo extends JpaRepository<Video, UUID> {

  List<Video> findAllByUser(final User user);
}
