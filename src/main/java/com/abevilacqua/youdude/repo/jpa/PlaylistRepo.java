package com.abevilacqua.youdude.repo.jpa;

import com.abevilacqua.youdude.model.Playlist;
import com.abevilacqua.youdude.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PlaylistRepo extends JpaRepository<Playlist, UUID> {

  List<Playlist> findAllByUser(final User user);
}
